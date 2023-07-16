package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.model.entity.SAllayModel;
import com.susen36.shiny.world.entity.SAllayEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SAllayRender extends MobRenderer<SAllayEntity, SAllayModel> {

    public SAllayRender(EntityRendererProvider.Context context) {
        super(context, new SAllayModel(context.bakeLayer(SAllayModel.LAYER_LOCATION)), 0.4F);
     }

    protected int getBlockLightLevel(SAllayEntity allay, BlockPos pos) {
        return 13;
    }
    public ResourceLocation getTextureLocation(SAllayEntity allay) {
        return new ResourceLocation(ShinyMob.MODID, "textures/entity/shiny_allay.png");
    }
}

