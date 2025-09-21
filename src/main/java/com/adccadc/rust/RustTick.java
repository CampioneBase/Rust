package com.adccadc.rust;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;

import java.util.Random;

public class RustTick {
    private static final Random random = new Random();
    private static long lastTrueTime = System.nanoTime();
    private static double nextInterval = generateNextInterval();

    // 生成下一个间隔时间（秒）
    private static double generateNextInterval() {
        if (random.nextDouble() < 0.0138) {
            // 1.38% 概率生成短间隔（0-1秒）
            return random.nextDouble();
        } else if (random.nextDouble() < 0.0123) {
            // 1.23% 概率生成长间隔（300-600秒）
            return 300 + 300 * random.nextDouble();
        } else {
            // 主要分布：对数正态分布
            double mu = Math.log(47.3);
            double sigma = Math.sqrt(2 * Math.log(68.27 / 47.3));
            return Math.exp(mu + sigma * random.nextGaussian());
        }
    }

    public static boolean tick(ServerWorld serverWorld) {
        if (serverWorld.getGameRules().getInt(GameRules.RANDOM_TICK_SPEED) == 0) {return false;}
        long currentTime = System.nanoTime();
        double elapsed = ((currentTime - lastTrueTime) / 1e9) * (serverWorld.getGameRules().getInt(GameRules.RANDOM_TICK_SPEED) / 3.0);

        if (elapsed >= nextInterval) {
            lastTrueTime = currentTime;
            nextInterval = generateNextInterval();
            return true;
        }
        return false;
    }
}
