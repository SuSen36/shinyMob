package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PhantomRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SPhantomRender extends PhantomRenderer {
    public SPhantomRender(EntityRendererProvider.Context p_174338_) {
        super(p_174338_);
    }
    public ResourceLocation getTextureLocation(Phantom p_115697_) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_phantom.png");
    }
}
