package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HuskRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SHuskRender extends HuskRenderer {
    public SHuskRender(EntityRendererProvider.Context p_174180_) {
        super(p_174180_);
    }
    public ResourceLocation getTextureLocation(Zombie p_114905_) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_husk.png");
    }
}
