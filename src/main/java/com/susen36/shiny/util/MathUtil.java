package com.susen36.shiny.util;

import net.minecraft.util.RandomSource;

public class MathUtil {
    public static int getRandomMinMax(RandomSource rand, int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
}
