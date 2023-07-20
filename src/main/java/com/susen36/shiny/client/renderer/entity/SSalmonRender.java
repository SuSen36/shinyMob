package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SalmonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Salmon;

public class SSalmonRender extends SalmonRenderer {
    public SSalmonRender(EntityRendererProvider.Context context) {
        super(context);
    }
    public ResourceLocation getTextureLocation(Salmon salmon) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_salmon.png");
    }
}
