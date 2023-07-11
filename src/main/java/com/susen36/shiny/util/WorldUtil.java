package com.susen36.shiny.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.levelgen.Heightmap;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class WorldUtil {
    @Nullable
    public static BlockPos findRandomSpawnPos(Level level, BlockPos center, int chance, int minRange, int maxRange, Predicate<BlockPos> predicate) {
        final int range = minRange, distance = maxRange - minRange;
        if(distance <= 0) {
            return null;
        }

        for (int i = 0; i < chance; ++i) {
            final float f = level.random.nextFloat() * ((float) Math.PI * 2F);
            final int radius = MathUtil.getRandomMinMax(level.getRandom(), minRange, maxRange);
            final int x = (int) (center.getX() + Math.floor(Math.cos(f) * radius));
            final int z = (int) (center.getZ() + Math.floor(Math.sin(f) * radius));
            final int y = level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);
            final BlockPos pos = new BlockPos(x, y, z);
            if (level.hasChunksAt(pos.offset(-range, -range, -range), pos.offset(range, range, range))) {
                if(predicate.test(pos) && level.getBrightness(LightLayer.BLOCK, pos) < 7) {
                    return pos;
                }
            }
        }
        return null;
    }
}
