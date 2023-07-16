package com.susen36.shiny;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static com.susen36.shiny.init.EntityInit.ENTITIES;
import static com.susen36.shiny.init.ItemsInit.ITEMS;

@Mod(ShinyMob.MODID)
public class ShinyMob {
    public static final String MODID = "shiny";
    public static final Logger LOGGER = LogUtils.getLogger();


    public ShinyMob() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus bus = MinecraftForge.EVENT_BUS;

        ITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
        };
    }

}
