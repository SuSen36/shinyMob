package com.susen36.shiny.client.renderer.entity;

import com.google.common.collect.Maps;
import com.susen36.shiny.ShinyMob;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PandaRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Panda;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;
@OnlyIn(Dist.CLIENT)
public class SPandaRender extends PandaRenderer {
    private static final Map<Panda.Gene, ResourceLocation> TEXTURES = Util.make(Maps.newEnumMap(Panda.Gene.class), (p_115647_) -> {
        p_115647_.put(Panda.Gene.NORMAL, new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_panda/panda.png"));
        p_115647_.put(Panda.Gene.LAZY, new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_panda/lazy_panda.png"));
        p_115647_.put(Panda.Gene.WORRIED, new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_panda/worried_panda.png"));
        p_115647_.put(Panda.Gene.PLAYFUL, new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_panda/playful_panda.png"));
        p_115647_.put(Panda.Gene.BROWN, new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_panda/brown_panda.png"));
        p_115647_.put(Panda.Gene.WEAK, new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_panda/weak_panda.png"));
        p_115647_.put(Panda.Gene.AGGRESSIVE, new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_panda/aggressive_panda.png"));
    });
    public SPandaRender(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(Panda p_115639_) {
        return TEXTURES.getOrDefault(p_115639_.getVariant(), TEXTURES.get(Panda.Gene.NORMAL));
    }
}
