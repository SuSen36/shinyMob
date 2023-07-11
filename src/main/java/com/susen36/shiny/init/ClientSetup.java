package com.susen36.shiny.init;


import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.renderer.entity.IdRenderer;

import com.susen36.shiny.client.renderer.entity.shiny.ShinyEnderManRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ShinyMob.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntitiesInit.ID.get(), IdRenderer::new);
        event.registerEntityRenderer(EntitiesInit.SHINY_ENDERMAN.get(), ShinyEnderManRenderer::new);
    }
}
