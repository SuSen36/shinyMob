package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.renderer.layer.GloemPumpkinLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.IronGolem;

public class SIronRender extends IronGolemRenderer {

    public SIronRender(EntityRendererProvider.Context context) {
        super(context);
        this.addLayer(new GloemPumpkinLayer<>(this, context.getBlockRenderDispatcher()));
    }
    public ResourceLocation getTextureLocation(IronGolem p_115012_) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_iron_golem.png");
    }

}
