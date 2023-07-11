package com.susen36.shiny.init;

import com.susen36.shiny.ShinyMob;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ShinyMob.MODID);
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties();
    public static final Item.Properties ITEM_PROPERTIES_HIDDEN = new Item.Properties();

    //Advancements
   // public static final RegistryObject<CloudArrowItem> CLOUD_ARROW_ITEM = ITEMS.register("cloud_arrow_item",
   //         CloudArrowItem::new);
   //

    public static final RegistryObject<Item> feather = ITEMS.register("feather", () -> new Item(ITEM_PROPERTIES_HIDDEN));

}
