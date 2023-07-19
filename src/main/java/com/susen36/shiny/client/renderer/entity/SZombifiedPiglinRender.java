package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.model.entity.SPiglinModel;
import com.susen36.shiny.world.entity.SZombifiedPiglinEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ArrowLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class SZombifiedPiglinRender extends MobRenderer<SZombifiedPiglinEntity, HumanoidModel<SZombifiedPiglinEntity>> {

    public SZombifiedPiglinRender(EntityRendererProvider.Context context) {
        super(context, new SPiglinModel(context.bakeLayer(SPiglinModel.LAYER_LOCATION),false), 0.4F);
        this.addLayer(new ArrowLayer(context, this));
        this.addLayer(new ElytraLayer<>(this, context.getModelSet()));
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    public ResourceLocation getTextureLocation(SZombifiedPiglinEntity mob) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_zombified_piglin.png");
    }
}

