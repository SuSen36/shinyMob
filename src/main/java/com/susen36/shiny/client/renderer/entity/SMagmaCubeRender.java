package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SMagmaCubeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MagmaCubeRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.MagmaCube;

public class SMagmaCubeRender extends MagmaCubeRenderer {
    private static final ResourceLocation MAGMACUBE_LOCATION = new ResourceLocation("textures/entity/slime/magmacube.png");
    private static final ResourceLocation SOLID_LOCATION = new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_magma_cube.png");

    public SMagmaCubeRender(EntityRendererProvider.Context context) {
        super(context);
    }
    protected int getBlockLightLevel(MagmaCube magmaCube, BlockPos p_115400_) {
        return ((SMagmaCubeEntity)magmaCube).getSolidState() ? 1:15;
    }
    public ResourceLocation getTextureLocation(MagmaCube magmaCube) {
        return ((SMagmaCubeEntity)magmaCube).getSolidState()? SOLID_LOCATION : MAGMACUBE_LOCATION;
    }
}
