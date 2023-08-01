package com.susen36.shiny.init;

import com.susen36.shiny.ShinyMob;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collection;

public class ItemsInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ShinyMob.MODID);
    public static final Collection<RegistryObject<Item>> SPAWN_EGGS = new ArrayList<>();

    public static final Item.Properties ITEM_PROPERTIES_HIDDEN = new Item.Properties();


    // public static final RegistryObject<MobBucketItem> SHINY_COD_BUCKET = ITEMS.register("shiny_cod_bucket",
    //         () -> new MobBucketItem(EntityInit.SHINY_COD.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));


   // public static final RegistryObject<MobBucketItem> SHINY_COD_BUCKET = ITEMS.register("shiny_cod_bucket",() -> new MobBucketItem(EntityInit.SHINY_ALLAY.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));
   public static final RegistryObject<Item> SHINY_COD_BUCKET = ITEMS.register("shiny_cod_bucket", () -> {
       return new MobBucketItem(EntityInit.SHINY_COD, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_AXOLOTL, new Item.Properties().stacksTo(1));
   });
    public static final RegistryObject<Item> SHINY_TROPICAL_FISH_BUCKET = ITEMS.register("shiny_tropical_fish_bucket", () -> {
        return new MobBucketItem(EntityInit.SHINY_TROPICAL_FISH, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_AXOLOTL, new Item.Properties().stacksTo(1));
    });
    public static final RegistryObject<Item> feather = ITEMS.register("feather", () -> new Item(ITEM_PROPERTIES_HIDDEN));

}
