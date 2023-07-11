package com.susen36.shiny.client.renderer.entity.shiny;

import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.renderer.entity.shiny.layer.ShinyEnderEyesLayer;
import com.susen36.shiny.world.entity.shiny.ShinyEnderManEntity;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShinyEnderManRenderer extends MobRenderer<ShinyEnderManEntity,EndermanModel<ShinyEnderManEntity>> {

    public ShinyEnderManRenderer(EntityRendererProvider.Context p_174324_) {
        super(p_174324_, new EndermanModel(p_174324_.bakeLayer(ModelLayers.ENDERMAN)), 0.7F);
        this.addLayer(new ShinyEnderEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ShinyEnderManEntity p_114482_) {
        return new ResourceLocation(ShinyMob.MODID,"textures/entity/shiny_enderman.png");
    }
}

