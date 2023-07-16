package com.susen36.shiny.client.renderer.layer;

import com.susen36.shiny.ShinyMob;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShinyEnderEyesLayer<T extends LivingEntity> extends EyesLayer<T, EndermanModel<T>> {
    private static final RenderType SHINY_ENDERMAN_EYES = RenderType.eyes(new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_enderman_eyes.png"));

    public ShinyEnderEyesLayer(RenderLayerParent<T, EndermanModel<T>> p_116981_) {
        super(p_116981_);
    }
    public RenderType renderType() {
        return SHINY_ENDERMAN_EYES;
    }
}
