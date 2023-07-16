package com.susen36.shiny.init;


import com.susen36.shiny.ShinyMob;
import com.susen36.shiny.client.model.entity.SAllayModel;
import com.susen36.shiny.client.model.entity.SCreeperModel;
import com.susen36.shiny.client.model.entity.SSnowGolemModel;
import com.susen36.shiny.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.StrayRenderer;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ShinyMob.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void onRegisterModel(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SAllayModel.LAYER_LOCATION, SAllayModel::createBodyLayer);
        event.registerLayerDefinition(SCreeperModel.LAYER_LOCATION, SCreeperModel::createBodyLayer);
        event.registerLayerDefinition(SSnowGolemModel.LAYER_LOCATION, SSnowGolemModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.SHINY_ENDERMAN.get(), SEnderManRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_SHEEP.get(), SSheepRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_ALLAY.get(), SAllayRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_CREEPER.get(), SCreeperRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_SNOW_GOLEM.get(), SSnowGolemRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_BLAZE.get(), SBlazeRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_WITHER_SKELETON.get(), WitherSkeletonRenderer::new);
        event.registerEntityRenderer(EntityInit.SHINY_GHAST.get(), SGhastRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_SKELETON.get(), SkeletonRenderer::new);
        event.registerEntityRenderer(EntityInit.SHINY_WARDEN.get(), SWardenRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_STRAY.get(), StrayRenderer::new);
        event.registerEntityRenderer(EntityInit.SHINY_HUSK.get(), SHuskRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_ZOMBIE.get(), SZombieRender::new);
        event.registerEntityRenderer(EntityInit.SHINY_STRIDER.get(), SStriderRender::new);


    }
}
