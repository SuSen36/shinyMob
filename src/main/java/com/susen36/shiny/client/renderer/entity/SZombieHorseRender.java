package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SZombieHorseEntity;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SZombieHorseRender extends MobRenderer<SZombieHorseEntity, HorseModel<SZombieHorseEntity>> {


    public SZombieHorseRender(EntityRendererProvider.Context context) {
        super(context, new HorseModel<>(context.bakeLayer(ModelLayers.HORSE)), 0.7f);

    }

    public ResourceLocation getTextureLocation(SZombieHorseEntity p_116274_) {
        return new ResourceLocation(ShinyMob.MODID,"textures/entity//shiny_zombie_horse.png");
    }
}