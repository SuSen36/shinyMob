package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SSkeletonHorseEntity;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SSkeletonHorseRender  extends MobRenderer<SSkeletonHorseEntity, HorseModel<SSkeletonHorseEntity>> {

    public SSkeletonHorseRender(EntityRendererProvider.Context context) {
        super(context, new HorseModel<>(context.bakeLayer(ModelLayers.HORSE)), 0.7f);

    }

    public ResourceLocation getTextureLocation(SSkeletonHorseEntity horse) {
        return new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_skeleton_horse.png");
    }
}