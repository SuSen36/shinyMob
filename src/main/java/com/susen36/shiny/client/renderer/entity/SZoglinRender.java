package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.model.entity.SHoglinModel;
import com.susen36.shiny.world.entity.SZoglinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SZoglinRender  extends MobRenderer<SZoglinEntity, SHoglinModel<SZoglinEntity>> {
    public SZoglinRender(EntityRendererProvider.Context context) {
        super(context, new SHoglinModel<>(context.bakeLayer(SHoglinModel.LAYER_LOCATION)), 0.7f);
    }

    public ResourceLocation getTextureLocation(SZoglinEntity sZoglinEntity) {
        return new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_zoglin.png");
    }
}
