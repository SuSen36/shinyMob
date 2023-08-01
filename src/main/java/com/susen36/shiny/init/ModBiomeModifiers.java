package com.susen36.shiny.init;

import com.mojang.serialization.Codec;
import com.susen36.shiny.ShinyMob;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, ShinyMob.MODID);

    public static final RegistryObject<Codec<SBiomeModifier>> SHINY_ENTITY_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("shiny_entity_modifier", () -> Codec.unit(SBiomeModifier.INSTANCE));

}
