package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.model.entity.SSnowGolemModel;
import com.susen36.shiny.world.entity.SSnowGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SSnowGolemRender extends MobRenderer<SSnowGolemEntity, SSnowGolemModel<SSnowGolemEntity>> {
    public SSnowGolemRender(EntityRendererProvider.Context context) {
        super(context, new SSnowGolemModel<>(context.bakeLayer(SSnowGolemModel.LAYER_LOCATION)),0.5f);
       }
    @Override
    public ResourceLocation getTextureLocation(SSnowGolemEntity entity) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_snow_golem.png");
    }
}
