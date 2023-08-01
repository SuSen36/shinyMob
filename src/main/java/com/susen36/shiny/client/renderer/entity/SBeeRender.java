package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.model.entity.SBeeModel;
import com.susen36.shiny.world.entity.SBeeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SBeeRender extends MobRenderer<SBeeEntity, SBeeModel<SBeeEntity>> {

    public SBeeRender(EntityRendererProvider.Context context) {
        super(context, new SBeeModel<>(context.bakeLayer(SBeeModel.LAYER_LOCATION)), 0.3F);
    }

    public ResourceLocation getTextureLocation(SBeeEntity p_114354_) {
        return new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_bee.png");
    }
}