package com.adccadc.rust;

import com.adccadc.rust.block.Modblocks;
import com.adccadc.rust.effect.ModEffects;
import com.adccadc.rust.entity.EntityReplace;
import com.adccadc.rust.entity.ModEntity;
import com.adccadc.rust.item.ItemReplace;
import com.adccadc.rust.item.ModItemGroups;
import com.adccadc.rust.item.Moditems;
import com.google.common.collect.BiMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Rust implements ModInitializer {
	public static final String MOD_ID = "rust";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void UseLater(
            ItemStack useItem, PlayerEntity player,
            Integer integer, BlockHitResult blockHitResult, EntityHitResult entityHitResult,
            ServerWorld serverWorld, Vec3d vec3d, BlockPos pos,
            ParticleEffect particle,
            ItemConvertible dropItem, Integer count
    ) {
        if (integer != null && blockHitResult == null && entityHitResult == null) {
            useItem.damage(integer, player);
        }
        if (blockHitResult != null && integer != null) {
                useItem.decrementUnlessCreative(integer, player);
        }
        if (entityHitResult != null && integer != null) {
                useItem.decrementUnlessCreative(integer, player);
        }

        if (particle != null) {
            if (vec3d != null) {
                serverWorld.spawnParticles(particle, vec3d.getX() + 0.5, vec3d.getY() + 0.5, vec3d.getZ() + 0.5,25, 0.4, 0.4, 0.4, 0.0);
            }
            if (pos != null) {
                serverWorld.spawnParticles(particle, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,25, 0.4, 0.4, 0.4, 0.0);
            }
        }

        if(dropItem != null) {
            ItemEntity drop = null;
            if (vec3d != null) {
                    drop = new ItemEntity(
                        serverWorld,
                            vec3d.getX() + 0.5, vec3d.getY() + 0.5, vec3d.getZ() + 0.5,
                        new ItemStack(dropItem, count)
                );
            }
            if (pos != null) {
                drop = new ItemEntity(
                        serverWorld,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        new ItemStack(dropItem, count)
                );
            }
            serverWorld.spawnEntity(drop);
        }
    }

	@Override
	public void onInitialize() {
        RustConfig.loadConfig();
        Moditems.initialize();
        ModItemGroups.register();
        Modblocks.initialize();
        ModEffects.initialize();
        ModEntity.initialize();

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
                        EntityReplace.ReplaceRustyEntityWithAttribute(serverWorld, box);
                        if(RustConfig.useLegacyOxidizeLogic()) {
                            // 旧版方块氧化机制
                            for (BlockPos pos : BlockPos.iterate((int) box.minX, (int) box.minY, (int) box.minZ, (int) box.maxX, (int) box.maxY, (int) box.maxZ)) {
                                BlockState state = world.getBlockState(pos);
                                Block block = state.getBlock();
                                Block increasesBlock = (Block)((BiMap)OxidizeMap.MOD_OXIDATION_LEVEL_INCREASES.get()).get(block);
                                if (increasesBlock != null) {
                                    if (random.nextDouble() > 0.8) {
                                        BlockUtils.PutWhichBlockWithAttribute(world, increasesBlock, state, pos);
                                        world.updateListeners(pos, state, increasesBlock.getDefaultState(), 0);
                                    }
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

                Block increasesBlock = (Block)((BiMap)HoneyMap.MOD_WAXING_LEVEL_INCREASES.get()).get(block);

                // 涂蜡
                if (increasesBlock != null) {
                    BlockUtils.PutWhichBlockWithAttribute(world, increasesBlock, state, pos);

                    UseLater(stack, player, 1, hitResult, null, (ServerWorld) world, null, pos, ParticleTypes.WAX_ON, null, null);
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

                Block decreasesBlock = (Block)((BiMap)HoneyMap.MOD_WAXING_LEVEL_DECREASES.get()).get(block);
                // 除蜡
                if (decreasesBlock != null) {

                    BlockUtils.PutWhichBlockWithAttribute(world, decreasesBlock, state, pos);

                    UseLater(stack, player, 1, null, null, (ServerWorld) world, null, pos, ParticleTypes.WAX_OFF, null, null);
                    // 播放剥离音效
                    world.playSound(null, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return ActionResult.SUCCESS;
                }

                // 铁质方块除锈
                decreasesBlock = (Block)((BiMap)OxidizeMap.MOD_OXIDATION_LEVEL_DECREASES.get()).get(block);
                if (decreasesBlock != null) {
                    BlockUtils.PutWhichBlockWithAttribute(world, decreasesBlock, state, pos);

                    UseLater(stack, player, 1, null, null, (ServerWorld) world, null, pos, null, Moditems.IRON_RUST, 1);
                    // 播放剥离音效
                    //world.playSound(null, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
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

                UseLater(null, null, null, null, null, (ServerWorld) world, null, pos, null, Moditems.VERDIGRIS, 1);

            }
            return ActionResult.PASS;
        });

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (world.isClient) return ActionResult.PASS;

            if (entity.getType() == ModEntity.RUSTY_IRON_GOLEM || entity.getType() == ModEntity.WAXED_IRON_GOLEM || entity.getType() == EntityType.IRON_GOLEM) {
                ItemStack stack = player.getStackInHand(hand);
                if(world instanceof ServerWorld serverWorld) {
                    if (stack.getItem() instanceof HoneycombItem && entity.getType() == EntityType.IRON_GOLEM) {
                        EntityReplace.ReplaceIronGolemWithAttribute(serverWorld, (IronGolemEntity) entity, false);
                        UseLater(stack, player, 1, null, hitResult, serverWorld, entity.getPos(), null, ParticleTypes.WAX_ON, null, null);
                        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        return ActionResult.SUCCESS;
                    }
                    if (stack.getItem() instanceof AxeItem && entity.getType() != EntityType.IRON_GOLEM) {
                        EntityReplace.ReplaceIronGolemWithAttribute(serverWorld, (IronGolemEntity) entity, null);
                        if (entity.getType() == ModEntity.RUSTY_IRON_GOLEM) {
                            UseLater(stack, player, 4, null, null, serverWorld, entity.getPos(), null, ParticleTypes.WAX_OFF, Moditems.IRON_RUST, 4);
                            world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        } else {
                            UseLater(stack, player, 4, null, null, serverWorld, entity.getPos(), null, ParticleTypes.WAX_OFF, null, null);
                            world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        }
                        return ActionResult.SUCCESS;
                    }
                }
            }

            return ActionResult.PASS;
        });
	}
}