package com.adccadc.rust.mixin.entity.iromGolem;

import com.adccadc.rust.Rust;
import com.adccadc.rust.RustTick;
import com.adccadc.rust.item.Moditems;
import com.adccadc.rust.manager.RustManager;
import com.adccadc.rust.proxy.IronGolemEntityProxy;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IronGolemEntity.class)
public abstract class IronGolemEntityMixin extends GolemEntity implements IronGolemEntityProxy
{
    // 锈蚀控制器
    @Unique
    public RustManager rust = new RustManager(new ItemStack(Moditems.IRON_RUST, 2), 3);

    public IronGolemEntityMixin(EntityType<? extends IronGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    public IronGolemEntityProxy proxy(){
        return this;
    }

    @Unique
    public RustManager getRust(){
        return this.rust;
    }

    @Inject(method = "writeCustomData",
            /*at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/iromGolem/mob/Angerable;writeAngerToData(Lnet/minecraft/world/World;Lnet/minecraft/storage/WriteView;)V",
                    shift = At.Shift.BEFORE
            )*/
            at = @At("HEAD")
    )
    private void writeCustomDataWithRust(WriteView view, CallbackInfo info){
        view.putInt("RustLevel", this.rust.getState().level);
        view.putBoolean("IsWaxed", this.rust.getState().isWaxed);
    }

    @Inject(method = "readCustomData",
            /*at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/iromGolem/mob/Angerable;readAngerFromData(Lnet/minecraft/world/World;Lnet/minecraft/storage/WriteView;)V",
                    shift = At.Shift.BEFORE
            )*/
            at = @At("HEAD")
    )
    private void readCustomDataWithRust(ReadView view, CallbackInfo info){
        this.rust.setLevel(view.getInt("RustLevel", 0));
        this.rust.setWaxed(view.getBoolean("IsWaxed", false));
    }

    @Inject(
            method = "interactMob",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void interactMobWithRustActions(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        ItemStack itemStack = player.getStackInHand(hand);
        // 是否为水桶
        if (itemStack.isOf(Items.WATER_BUCKET)){
            // 处理物品
            if(!player.isCreative()){
                // 将水桶换成空桶
                ItemStack newItemStack = ItemUsage.exchangeStack(itemStack, player, Items.BUCKET.getDefaultStack());
                player.setStackInHand(hand, newItemStack);
            }
            // 尝试执行一次锈蚀
            if(!(this.rust.tryRusted())){
                // 生锈失败
                cir.setReturnValue(ActionResult.FAIL);
            }else{
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        // 物品是否含有斧头标签
        } else if (itemStack.isIn(ItemTags.AXES)){
            // 执行一次除锈
            ItemStack dropStack;
            if (((dropStack = this.rust.tryDerusted()) == null)){
                // 除锈失败
                cir.setReturnValue(ActionResult.FAIL);
            } else {
                World world = this.getWorld();
                if (world instanceof ServerWorld serverWorld) {
                    // 生成掉落物
                    ItemEntity drop = this.dropStack(serverWorld, dropStack, 1.5F);
                    // 播放音效
                    this.playSound(SoundEvents.ITEM_AXE_WAX_OFF, 1.0F, 1.0F);
                    // 播放粒子
                    serverWorld.spawnParticles(
                            ParticleTypes.WAX_OFF,
                            this.getX(),
                            this.getY() + 1.0F,
                            this.getZ(),
                            35,
                            0.4F,
                            0.8F,
                            0.4F,
                            0.1F
                    );
                    // 处理物品
                    itemStack.damage(1, player);
                    cir.setReturnValue(ActionResult.SUCCESS);
                }
            }
        // 物品是否为蜜脾
        } else if (itemStack.isOf(Items.HONEYCOMB) && itemStack.getCount() >= 2) {
            // 尝试上蜡
            if (!this.rust.tryWaxed()){
                // 上蜡失败
                cir.setReturnValue(ActionResult.FAIL);
            } else {
                World world = this.getWorld();
                if(world instanceof ServerWorld serverWorld){
                    // 播放音效
                    this.playSound(SoundEvents.ITEM_HONEYCOMB_WAX_ON, 1.0F, 1.0F);
                    // 播放粒子
                    serverWorld.spawnParticles(
                            ParticleTypes.WAX_ON,
                            this.getX(),
                            this.getY() + 1.0F,
                            this.getZ(),
                            35,
                            0.4F,
                            0.8F,
                            0.4F,
                            0.1F
                    );
                    // 处理物品
                    itemStack.decrementUnlessCreative(2, player);
                    cir.setReturnValue(ActionResult.SUCCESS);
                }
            }
        }
    }

    @Override
    // 在名称前添加锈蚀状态
    protected Text getDefaultName(){
        return Text.of(this.rust.getName() + this.getType().getName().getString());
    }
/*
    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient && this.age % 5 == 0) {
            this.updateGoalControls();
        }
        if (!this.getWorld().isClient()) {
            if (random.nextDouble() < 1 && RustTick.tick((ServerWorld) this.getWorld())) {
                this.rust.tryRusted();
            }
        }
    }
*/
}
