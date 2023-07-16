package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.StriderRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Strider;

public class SStriderRender extends StriderRenderer {
    public SStriderRender(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(Strider p_234791_) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_strider.png");
    }

}
