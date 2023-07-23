package com.susen36.shiny.client.renderer.layer;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.world.entity.SHorseEntity;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShinyHorseEyesLayer extends EyesLayer<SHorseEntity, HorseModel<SHorseEntity>> {
    private static final RenderType SHINY_HORSE_EYES = RenderType.eyes(new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_horse/horse_eye.png"));

    public ShinyHorseEyesLayer(RenderLayerParent<SHorseEntity, HorseModel<SHorseEntity>> p_174496_) {
        super(p_174496_);
    }
    public RenderType renderType() {
        return SHINY_HORSE_EYES;
    }
}

