package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GoatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SGoatRender extends GoatRenderer {
    public SGoatRender(EntityRendererProvider.Context context) {
        super(context);
    }
    public ResourceLocation getTextureLocation(Goat goat) {
        return  new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_goat.png");
    }
}
