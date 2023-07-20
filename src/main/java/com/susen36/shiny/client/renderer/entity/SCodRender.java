package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.renderer.entity.CodRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SCodRender extends CodRenderer {
    public SCodRender(EntityRendererProvider.Context context) {
        super(context);
    }
    public ResourceLocation getTextureLocation(Cod cod) {
        return  new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_cod.png");
    }
}
