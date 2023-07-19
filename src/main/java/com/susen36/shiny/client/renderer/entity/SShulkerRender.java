package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.client.renderer.layer.ShulkerChestLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ShulkerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SShulkerRender extends ShulkerRenderer {


    public SShulkerRender(EntityRendererProvider.Context context) {
        super(context);
        this.addLayer(new ShulkerChestLayer(this, context.getItemRenderer()));
    }

}
