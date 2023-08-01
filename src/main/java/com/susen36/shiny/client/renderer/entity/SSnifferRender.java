package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SnifferRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.sniffer.Sniffer;

public class SSnifferRender extends SnifferRenderer {
    public SSnifferRender(EntityRendererProvider.Context context) {
        super(context);
    }
    public ResourceLocation getTextureLocation(Sniffer p_273552_) {
        return new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_sniffer.png");
    }
}
