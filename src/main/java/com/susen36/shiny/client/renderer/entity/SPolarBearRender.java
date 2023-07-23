package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PolarBearRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SPolarBearRender extends PolarBearRenderer {
    public SPolarBearRender(EntityRendererProvider.Context context) {
        super(context);
    }
    public ResourceLocation getTextureLocation(PolarBear bear) {
        return new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_polar_bear.png");
    }
}
