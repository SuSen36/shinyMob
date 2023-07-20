package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.client.model.entity.SHoglinModel;
import com.susen36.shiny.world.entity.SHoglinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SHoglinRender extends MobRenderer<SHoglinEntity, SHoglinModel<SHoglinEntity>> {
    public SHoglinRender(EntityRendererProvider.Context context) {
        super(context, new SHoglinModel<>(context.bakeLayer(SHoglinModel.LAYER_LOCATION)), 0.7f);
    }

    protected boolean isShaking(SHoglinEntity hoglin) {
        return super.isShaking(hoglin) || hoglin.isConverting();
    }
    public ResourceLocation getTextureLocation(SHoglinEntity sHoglinEntity) {
        return  new ResourceLocation("textures/entity/hoglin/hoglin.png");
    }
}