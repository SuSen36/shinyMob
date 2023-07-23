package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitchRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Witch;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SWitchRender extends WitchRenderer {
    public SWitchRender(EntityRendererProvider.Context context) {
        super(context);
    }
    public ResourceLocation getTextureLocation(Witch p_116410_) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_witch.png");
    }

}
