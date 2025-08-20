package com.adccadc.rust;

import com.adccadc.rust.block.Modblocks;
import com.adccadc.rust.effect.ModEffects;
import com.adccadc.rust.item.ItemReplace;
import com.adccadc.rust.item.ModItemGroups;
import com.adccadc.rust.item.Moditems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class Rust implements ModInitializer {
	public static final String MOD_ID = "rust";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        Moditems.initialize();
        ModItemGroups.register();
        Modblocks.initialize();
        ModEffects.initialize();

        // 破伤风
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);
            Random random = new Random();
            if (world.isClient) return ActionResult.PASS;
            if (List.of(Moditems.RUSTY_IRON_SWORD, Moditems.RUSTY_IRON_AXE, Moditems.RUSTY_IRON_PICKAXE, Moditems.RUSTY_IRON_SHOVEL, Moditems.RUSTY_IRON_HOE).contains(stack.getItem()) && random.nextDouble() > 0.7) {
                if (entity instanceof LivingEntity livingEntity) {
                    StatusEffectInstance tetanusEffect = livingEntity.getStatusEffect(ModEffects.TETANUS);
                    if (tetanusEffect != null) {
                        if (random.nextDouble() < 0.5) {
                            // 破伤风buff延长10s
                            int newDuration = tetanusEffect.getDuration() + 200;
                            livingEntity.addStatusEffect(new StatusEffectInstance(
                                    ModEffects.TETANUS,
                                    newDuration,
                                    tetanusEffect.getAmplifier(),
                                    tetanusEffect.isAmbient(),
                                    tetanusEffect.shouldShowParticles(),
                                    tetanusEffect.shouldShowIcon()
                            ));
                        } else {
                            // 破伤风等级+1 （不超过4级）
                            int newAmplifier = Math.min(tetanusEffect.getAmplifier() + 1, 4);
                            livingEntity.addStatusEffect(new StatusEffectInstance(
                                    ModEffects.TETANUS,
                                    tetanusEffect.getDuration(),
                                    newAmplifier,
                                    tetanusEffect.isAmbient(),
                                    tetanusEffect.shouldShowParticles(),
                                    tetanusEffect.shouldShowIcon()
                            ));
                        }
                    } else {
                        // 给与破伤风1级 10s
                        livingEntity.addStatusEffect(new StatusEffectInstance(
                                ModEffects.TETANUS,
                                200,
                                0
                        ));
                    }
                    return ActionResult.PASS;
                }
            }
            return ActionResult.PASS;
        });

        // 氧化过程
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (world.getTime() % 6000 == 0) { // 5min一次
                if (world instanceof ServerWorld serverWorld) {
                    for (PlayerEntity player : serverWorld.getPlayers()) {
                        Random random = new Random();
                        Box box = new Box(player.getBlockPos()).expand(32, 32, 32);
                        ItemReplace.ChangeCorrosionToolWithAttribute(
                                List.of(Items.IRON_SWORD, Items.IRON_AXE, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_HOE, Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS),
                                List.of(Moditems.RUSTY_IRON_SWORD, Moditems.RUSTY_IRON_AXE, Moditems.RUSTY_IRON_PICKAXE, Moditems.RUSTY_IRON_SHOVEL, Moditems.RUSTY_IRON_HOE, Moditems.RUSTY_IRON_HELMET, Moditems.RUSTY_IRON_CHESTPLATE, Moditems.RUSTY_IRON_LEGGINGS, Moditems.RUSTY_IRON_BOOTS),
                                player);
                        for (BlockPos pos : BlockPos.iterate((int) box.minX, (int) box.minY, (int) box.minZ, (int) box.maxX, (int) box.maxY, (int) box.maxZ)) {
                            BlockState state = world.getBlockState(pos);
                            Block block = state.getBlock();
                            Block nextBlock = OxidizeMap.IRONBLOCK_OXIDATION_MAP.get(block);
                            if (nextBlock != null) {
                                if (random.nextDouble() > 0.8) {
                                    BlockUtils.PutWhichBlockWithAttribute(world, nextBlock, state, pos);
                                    world.updateListeners(pos, state, nextBlock.getDefaultState(), 0);
                                }
                            }
                        }
                    }
                }
            }
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClient) return ActionResult.PASS;

            ItemStack stack = player.getStackInHand(hand);
            // 蜜脾右键
            if (stack.getItem() instanceof HoneycombItem) {

                BlockPos pos = hitResult.getBlockPos();
                BlockState state = world.getBlockState(pos);
                Block block = state.getBlock();

                Map<Block, Block> waxableBlocks = HoneyMap.getWaxableBlocks();
                // 涂蜡
                if (waxableBlocks.containsKey(block)) {
                    Block waxedBlock = waxableBlocks.get(block);
                    if (HoneyMap.WAXED_BLOCKS.contains(block)) {
                        return ActionResult.PASS;
                    }

                    BlockUtils.PutWhichBlockWithAttribute(world, waxedBlock, state, pos);
                    // 粒子效果
                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(ParticleTypes.WAX_ON, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.4, 0.4, 0.4, 0.0);
                    }
                    // 消耗蜜脾
                    if (!player.isCreative()) {
                        stack.useOnBlock(new ItemUsageContext(player, hand, hitResult));
                    }
                    // 播放涂蜡音效
                    world.playSound(null, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return ActionResult.SUCCESS;
                }
            }
            // 斧头右键
            if (stack.getItem() instanceof AxeItem) {

                BlockPos pos = hitResult.getBlockPos();
                BlockState state = world.getBlockState(pos);
                Block block = state.getBlock();
                // 除蜡
                if (HoneyMap.UNWAXABLE_BLOCKS.containsKey(block)) {
                    Block unwaxedBlock = HoneyMap.UNWAXABLE_BLOCKS.get(block);

                    BlockUtils.PutWhichBlockWithAttribute(world, unwaxedBlock, state, pos);
                    // 粒子效果
                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(ParticleTypes.WAX_OFF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.4, 0.4, 0.4, 0.0);
                    }
                    // 消耗耐久
                    stack.damage(1, player);
                    // 播放剥离音效
                    world.playSound(null, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return ActionResult.SUCCESS;
                }

                // 铁质方块除锈
                Block previousBlock = OxidizeMap.IRONBLOCK_DEOXIDATION_MAP.get(block);
                if (previousBlock != null) {
                    BlockUtils.PutWhichBlockWithAttribute(world, previousBlock, state, pos);
                    // 粒子效果
                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(new DustParticleEffect(0x563E3E, 1.0f), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.4, 0.4, 0.4, 0.0);
                    }
                    // 掉铁锈
                    ItemEntity drop = new ItemEntity(
                            world,
                            pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                            new ItemStack(Moditems.IRON_RUST, 1)
                    );
                    world.spawnEntity(drop);
                    // 消耗耐久
                    stack.damage(1, player);
                    // 播放剥离音效
                    world.playSound(null, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return ActionResult.SUCCESS;
                }

                // 铜质方块除锈
                if (!(block instanceof Oxidizable)) return ActionResult.PASS;

                Optional<Block> decreasedBlock = Oxidizable.getDecreasedOxidationBlock(state.getBlock());
                if (decreasedBlock.isEmpty()) return ActionResult.PASS;

                // 铜门 铜活版门检测
                if (block instanceof DoorBlock || block instanceof TrapdoorBlock) {
                    if (!player.isSneaking()) return ActionResult.PASS;
                }

                //掉铜锈
                ItemEntity drop = new ItemEntity(
                        world,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        new ItemStack(Moditems.VERDIGRIS, 1)
                );
                world.spawnEntity(drop);

                // 消耗耐久
                stack.damage(1, player);

                // 播放剥离音效
                world.playSound(null, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            return ActionResult.PASS;
        });
	}
}