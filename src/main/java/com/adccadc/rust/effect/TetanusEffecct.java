package com.adccadc.rust.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;

public class TetanusEffecct extends StatusEffect {
    public TetanusEffecct() {
        super(
                StatusEffectCategory.BENEFICIAL,
                0x744f4b);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        return true;
    }

    @Override
    public void onEntityDamage(ServerWorld world, LivingEntity entity, int amplifier, DamageSource source, float amount) {
        Random random = entity.getWorld().getRandom();
        int effectType = random.nextInt(30);
        switch (effectType) {
            case 0: // 虚弱 buff等级+1级 5秒
                entity.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.WEAKNESS,
                        100,
                        amplifier + 1
                ));
                break;

            case 1: // 缓慢 buff等级+1级 5秒
                entity.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SLOWNESS,
                        100,
                        amplifier + 1
                ));
                break;

            case 2: // 受到 buff等级点魔法伤害
                entity.damage((ServerWorld) entity.getWorld(), entity.getDamageSources().magic(), amplifier);
                break;

            default:
                break;
        }
    }
}