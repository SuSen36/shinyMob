package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.client.renderer.layer.TurtleMapLayer;
import com.susen36.shiny.world.entity.STurtleEntity;
import net.minecraft.client.model.TurtleModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class STurtleRender extends MobRenderer<STurtleEntity, TurtleModel<STurtleEntity>> {

    public STurtleRender(EntityRendererProvider.Context context) {
        super(context, new TurtleModel<>(context.bakeLayer(ModelLayers.TURTLE)), 0.7F);
          this.addLayer(new TurtleMapLayer<>(this, context.getItemRenderer()));
    }
    @Override
    public ResourceLocation getTextureLocation(STurtleEntity p_114482_) {
        return new ResourceLocation("textures/entity/turtle/big_sea_turtle.png");
    }
}
