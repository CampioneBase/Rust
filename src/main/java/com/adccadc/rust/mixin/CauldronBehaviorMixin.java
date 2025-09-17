package com.adccadc.rust.mixin;

import com.adccadc.rust.CauldronMap;
import com.adccadc.rust.block.OxidizableLeveledCauldronBlock;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.Predicate;

import static net.minecraft.block.cauldron.CauldronBehavior.*;

@Mixin(CauldronBehavior.class)
public interface CauldronBehaviorMixin {

    @Unique
    private static ActionResult fillCauldron1(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, BlockState state, SoundEvent soundEvent) {
        if (!world.isClient) {
            Item item = stack.getItem();
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
            player.incrementStat(Stats.FILL_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, state);
            world.playSound((Entity)null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent((Entity)null, GameEvent.FLUID_PLACE, pos);
        }

        return ActionResult.SUCCESS;
    }

    @Unique
    private static ActionResult cleanShulkerBox(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        Block block = Block.getBlockFromItem(stack.getItem());
        if (!(block instanceof ShulkerBoxBlock)) {
            return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
        } else {
            if (!world.isClient) {
                ItemStack itemStack = stack.copyComponentsToNewStack(Blocks.SHULKER_BOX, 1);
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, itemStack, false));
                player.incrementStat(Stats.CLEAN_SHULKER_BOX);
                LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

    @Unique
    private static ActionResult cleanBanner(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        BannerPatternsComponent bannerPatternsComponent = (BannerPatternsComponent)stack.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT);
        if (bannerPatternsComponent.layers().isEmpty()) {
            return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
        } else {
            if (!world.isClient) {
                ItemStack itemStack = stack.copyWithCount(1);
                itemStack.set(DataComponentTypes.BANNER_PATTERNS, bannerPatternsComponent.withoutTopLayer());
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, itemStack, false));
                player.incrementStat(Stats.CLEAN_BANNER);
                LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

    @Unique
    private static ActionResult cleanArmor(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        if (!stack.isIn(ItemTags.DYEABLE)) {
            return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
        } else if (!stack.contains(DataComponentTypes.DYED_COLOR)) {
            return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
        } else {
            if (!world.isClient) {
                stack.remove(DataComponentTypes.DYED_COLOR);
                player.incrementStat(Stats.CLEAN_ARMOR);
                LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

    @Unique
    private static boolean isUnderwater(World world, BlockPos pos) {
        FluidState fluidState = world.getFluidState(pos.up());
        return fluidState.isIn(FluidTags.WATER);
    }

    @Inject(
            method = "registerBehavior",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyRegisterBehavior(CallbackInfo ci) {
        Map<Item, CauldronBehavior> map = EMPTY_CAULDRON_BEHAVIOR.map();
        registerBucketBehavior(map);
        map.put(Items.POTION, (CauldronBehavior)(state, world, pos, player, hand, stack) -> {
            PotionContentsComponent potionContentsComponent = (PotionContentsComponent)stack.get(DataComponentTypes.POTION_CONTENTS);
            if (potionContentsComponent != null && potionContentsComponent.matches(Potions.WATER)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    Block block = CauldronMap.CAULDRON_TO_WATER_CAULDRON.get(state.getBlock());
                    world.setBlockState(pos, block.getDefaultState());
                    world.playSound((Entity)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent((Entity)null, GameEvent.FLUID_PLACE, pos);
                }

                return ActionResult.SUCCESS;
            } else {
                return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
            }
        });
        Map<Item, CauldronBehavior> map2 = WATER_CAULDRON_BEHAVIOR.map();
        registerBucketBehavior(map2);
        map2.put(Items.BUCKET, (CauldronBehavior)(state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.WATER_BUCKET), (statex) -> (Integer)statex.get(LeveledCauldronBlock.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL));
        map2.put(Items.GLASS_BOTTLE, (CauldronBehavior)(state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, PotionContentsComponent.createStack(Items.POTION, Potions.WATER)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                OxidizableLeveledCauldronBlock.decrementFluidLevel(state, world, pos);
                world.playSound((Entity)null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent((Entity)null, GameEvent.FLUID_PICKUP, pos);
            }

            return ActionResult.SUCCESS;
        });
        map2.put(Items.POTION, (CauldronBehavior)(state, world, pos, player, hand, stack) -> {
            if ((Integer)state.get(LeveledCauldronBlock.LEVEL) == 3) {
                return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
            } else {
                PotionContentsComponent potionContentsComponent = (PotionContentsComponent)stack.get(DataComponentTypes.POTION_CONTENTS);
                if (potionContentsComponent != null && potionContentsComponent.matches(Potions.WATER)) {
                    if (!world.isClient) {
                        player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                        player.incrementStat(Stats.USE_CAULDRON);
                        player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                        world.setBlockState(pos, (BlockState) state.cycle(LeveledCauldronBlock.LEVEL));
                        world.playSound((Entity)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        world.emitGameEvent((Entity)null, GameEvent.FLUID_PLACE, pos);
                    }

                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
                }
            }
        });
        map2.put(Items.LEATHER_BOOTS, CauldronBehaviorMixin::cleanArmor);
        map2.put(Items.LEATHER_LEGGINGS, CauldronBehaviorMixin::cleanArmor);
        map2.put(Items.LEATHER_CHESTPLATE, CauldronBehaviorMixin::cleanArmor);
        map2.put(Items.LEATHER_HELMET, CauldronBehaviorMixin::cleanArmor);
        map2.put(Items.LEATHER_HORSE_ARMOR, CauldronBehaviorMixin::cleanArmor);
        map2.put(Items.WOLF_ARMOR, CauldronBehaviorMixin::cleanArmor);
        map2.put(Items.WHITE_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.GRAY_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.BLACK_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.BLUE_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.BROWN_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.CYAN_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.GREEN_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.LIGHT_BLUE_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.LIGHT_GRAY_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.LIME_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.MAGENTA_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.ORANGE_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.PINK_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.PURPLE_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.RED_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.YELLOW_BANNER, CauldronBehaviorMixin::cleanBanner);
        map2.put(Items.WHITE_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.GRAY_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.BLACK_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.BLUE_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.BROWN_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.CYAN_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.GREEN_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.LIGHT_BLUE_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.LIGHT_GRAY_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.LIME_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.MAGENTA_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.ORANGE_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.PINK_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.PURPLE_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.RED_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        map2.put(Items.YELLOW_SHULKER_BOX, CauldronBehaviorMixin::cleanShulkerBox);
        Map<Item, CauldronBehavior> map3 = LAVA_CAULDRON_BEHAVIOR.map();
        map3.put(Items.BUCKET, (CauldronBehavior)(state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.LAVA_BUCKET), (statex) -> true, SoundEvents.ITEM_BUCKET_FILL_LAVA));
        registerBucketBehavior(map3);
        Map<Item, CauldronBehavior> map4 = POWDER_SNOW_CAULDRON_BEHAVIOR.map();
        map4.put(Items.BUCKET, (CauldronBehavior)(state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.POWDER_SNOW_BUCKET), (statex) -> (Integer)statex.get(LeveledCauldronBlock.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL_POWDER_SNOW));
        registerBucketBehavior(map4);
        ci.cancel();
    }

    @Inject(
            method = "emptyCauldron",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyEmptyCauldron(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, ItemStack output, Predicate<BlockState> fullPredicate, SoundEvent soundEvent, CallbackInfoReturnable<ActionResult> cir) {
        if (!fullPredicate.test(state)) {
            cir.setReturnValue(ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION);
            cir.cancel();
        } else {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, output));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, CauldronMap.CAULDRON_TO_EMPTY_CAULDRON.get(state.getBlock()).getDefaultState());
                world.playSound((Entity)null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent((Entity)null, GameEvent.FLUID_PICKUP, pos);
            }
            cir.setReturnValue(ActionResult.SUCCESS);
            cir.cancel();
        }
    }

    @Inject(
            method = "tryFillWithWater",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyTtyFillWithWater(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, CallbackInfoReturnable<ActionResult> cir) {
        Block block = CauldronMap.CAULDRON_TO_WATER_CAULDRON.get(state.getBlock());
        if (block != null) {
            cir.setReturnValue(fillCauldron1(world, pos, player, hand, stack, (BlockState) block.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3), SoundEvents.ITEM_BUCKET_EMPTY));
            cir.cancel();
        }
    }

    @Inject(
            method = "tryFillWithLava",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyTryFillWithLava(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, CallbackInfoReturnable<ActionResult> cir) {
        Block block = CauldronMap.CAULDRON_TO_LAVA_CAULDRON.get(state.getBlock());
        if (block != null) {
            cir.setReturnValue((ActionResult)(isUnderwater(world, pos) ? ActionResult.CONSUME : fillCauldron(world, pos, player, hand, stack, block.getDefaultState(), SoundEvents.ITEM_BUCKET_EMPTY_LAVA)));
            cir.cancel();
        }
    }

    @Inject(
            method = "tryFillWithPowderSnow",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyTryFillWithPowderSnow(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, CallbackInfoReturnable<ActionResult> cir) {
        Block block = CauldronMap.CAULDRON_TO_POWDER_SNOW_CAULDRON.get(state.getBlock());
        if (block != null) {
            cir.setReturnValue((ActionResult)(isUnderwater(world, pos) ? ActionResult.CONSUME : fillCauldron(world, pos, player, hand, stack, (BlockState)block.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3), SoundEvents.ITEM_BUCKET_EMPTY_POWDER_SNOW)));
            cir.cancel();
        }
    }
}
