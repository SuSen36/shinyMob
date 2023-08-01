package com.susen36.shiny.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class SBiomeModifier implements BiomeModifier {
    public static final SBiomeModifier INSTANCE = new SBiomeModifier();


    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && biome.containsTag(BiomeTags.IS_OVERWORLD) && !biome.is(Biomes.DEEP_DARK) && !biome.is(Tags.Biomes.IS_VOID)) {
            builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityInit.SHINY_ENDERMAN.get(), 80, 2, 3));

            if (biome.is(Tags.Biomes.IS_PLAINS) && !biome.is(Tags.Biomes.IS_COLD) && !biome.is(Tags.Biomes.IS_HOT)) {
                builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityInit.SHINY_SHEEP.get(), 12, 3, 6));


                if (biome.is(Tags.Biomes.IS_PLAINS) && !biome.is(Tags.Biomes.IS_COLD) && !biome.is(Tags.Biomes.IS_HOT) || biome.is(Tags.Biomes.IS_SWAMP) || biome.is(Biomes.RIVER)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityInit.SHINY_GLOW_SQUID.get(), 6, 3, 6));
                }

                if (biome.is(Tags.Biomes.IS_SNOWY) && biome.is(Tags.Biomes.IS_COLD) || biome.is(Tags.Biomes.IS_COLD)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityInit.SHINY_SNOW_GOLEM.get(), 2, 3, 6));
                }
                if (biome.is(Tags.Biomes.IS_PLAINS)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityInit.SHINY_COW.get(), 10, 4, 6));
                }

                if (biome.is(Tags.Biomes.IS_PLAINS)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityInit.SHINY_PIG.get(), 10, 3, 6));
                }
                if (biome.is(Tags.Biomes.IS_PLAINS)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityInit.SHINY_SHULKER.get(), 8, 3, 6));
                }
                if (biome.is(Tags.Biomes.IS_PLAINS)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityInit.SHINY_PIGLIN.get(), 2, 3, 6));
                }

                if (biome.is(Tags.Biomes.IS_PLAINS)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityInit.SHINY_HOGLIN.get(), 2, 3, 6));
                }

                if (biome.is(Tags.Biomes.IS_MOUNTAIN)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.SHINY_OCELOT.get(), 2, 3, 6));
                }
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return ModBiomeModifiers.SHINY_ENTITY_MODIFIER_TYPE.get();
    }
}