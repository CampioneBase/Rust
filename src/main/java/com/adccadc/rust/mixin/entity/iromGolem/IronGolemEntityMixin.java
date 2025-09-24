package com.adccadc.rust.mixin.entity.iromGolem;

import com.adccadc.rust.Rust;
import com.adccadc.rust.RustConfig;
import com.adccadc.rust.RustTick;
import com.adccadc.rust.effect.ModEffects;
import com.adccadc.rust.item.Moditems;
import com.adccadc.rust.manager.RustManager;
import com.adccadc.rust.proxy.IronGolemEntityProxy;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
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
    public RustManager rust = new RustManager(new ItemStack(Moditems.IRON_RUST, 4), 3);

    @Unique
    public int rustLayTime = 9000;

    public IronGolemEntityMixin(EntityType<? extends IronGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    public RustManager getRust(){
        return this.rust;
    }

    @Unique
    protected Float AttackDamage() {
        float damage = (float) this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
        if (!this.rust.getState().isWaxed) {
            switch (this.rust.getState().level) {
                case 0 -> damage = (float) this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
                case 1 -> damage = (float) (this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE) - (15.0F - (float) RustConfig.getExposed_IG().get(1)));
                case 2 -> damage = (float) (this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE) - (15.0F - (float) RustConfig.getWeathered_IG().get(1)));
                case 3 -> damage = (float) (this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE) - (15.0F - (float) RustConfig.getOxidized_IG().get(1)));
            }
        } else {
            switch (this.rust.getState().level) {
                case 0 -> damage = (float) (this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE) - (15.0F - (float) RustConfig.getWaxed_IG().get(1)));
                case 1 -> damage = (float) (this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE) - (15.0F - (float) RustConfig.getWaxed_exposed_IG().get(1)));
                case 2 -> damage = (float) (this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE) - (15.0F - (float) RustConfig.getWaxed_weathered_IG().get(1)));
                case 3 -> damage = (float) (this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE) - (15.0F - (float) RustConfig.getWaxed_oxidized_IG().get(1)));
            }
        }
        return damage;
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

        }

        // 物品是否含有斧头标签
        if (itemStack.isIn(ItemTags.AXES)){
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
                    this.playSound(SoundEvents.ITEM_AXE_SCRAPE, 1.0F, 1.0F);
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
                    itemStack.damage(4, player);
                    cir.setReturnValue(ActionResult.SUCCESS);
                }
            }
        // 物品是否为蜜脾
        } else if (itemStack.isOf(Items.HONEYCOMB) && itemStack.getCount() >= 4) {
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
                    itemStack.decrementUnlessCreative(4, player);
                    cir.setReturnValue(ActionResult.SUCCESS);
                }
            }
        }
    }

    @Inject(
            method = "getAttackDamage",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getAttackDamage(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(AttackDamage());
        cir.cancel();
    }

    @Inject(
            method = "tryAttack",
            at = @At("RETURN")
    )
    public void tryAttack(ServerWorld world, Entity target, CallbackInfoReturnable<Boolean> cir) {
        float f1 = AttackDamage();
        float g1 = (int)f1 > 0 ? f1 / 2.0F + (float)this.random.nextInt((int)f1) : f1;
        Rust.LOGGER.info("铁傀儡伤害：{}", g1);
        DamageSource damageSource = this.getDamageSources().mobAttack(this);
        boolean bl1 = target.damage(world, damageSource, g1);
        if (bl1 && target instanceof LivingEntity livingEntity) {
            StatusEffectInstance tetanusEffect = livingEntity.getStatusEffect(ModEffects.TETANUS);
            if (tetanusEffect == null) {
                // 给与破伤风1级 10s
                livingEntity.addStatusEffect(new StatusEffectInstance(
                        ModEffects.TETANUS,
                        200,
                        0
                ));
            }
        }
    }

    @Override
    // 在名称前添加锈蚀状态
    protected Text getDefaultName(){
        return Text.of(this.rust.getName() + this.getType().getName().getString());
    }

    @Override
    public float getMovementSpeed() {
        float[] speed = {
                (float) this.getAttributeValue(EntityAttributes.MOVEMENT_SPEED),
                RustConfig.getExposed_IG().getFirst(),
                RustConfig.getWeathered_IG().getFirst(),
                RustConfig.getOxidized_IG().getFirst()
                };
        float[] waxed_speed = {
                RustConfig.getWaxed_IG().getFirst(),
                RustConfig.getWaxed_exposed_IG().getFirst(),
                RustConfig.getWaxed_weathered_IG().getFirst(),
                RustConfig.getWaxed_oxidized_IG().getFirst(),
        };
        return this.rust.getState().isWaxed ? waxed_speed[this.rust.getState().level] : speed[this.rust.getState().level];
    }

    @Inject(
            method = "tickMovement",
            at = @At("TAIL")
    )
    public void tickMovementWithRust(CallbackInfo ci){
        int multiply = this.isTouchingWater() ? 2 : 1;
        if(this.rust.canRusted() && (this.rustLayTime -= multiply) <= 0){
            this.rust.tryRusted();
            this.rustLayTime = 9000;
        }
    }
}
