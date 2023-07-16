package com.susen36.shiny.client.renderer.entity;

import com.susen36.shiny.client.renderer.layer.SheepBedLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SSheepRender extends SheepRenderer {

    public SSheepRender(EntityRendererProvider.Context context) {
        super(context);
        this.addLayer(new SheepBedLayer(this, context.getBlockRenderDispatcher()));
    }

}
