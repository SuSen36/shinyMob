package com.susen36.shiny.data;

import com.susen36.shiny.ShinyMob;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ShinyMob.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (Item i : ForgeRegistries.ITEMS) {
            if (i instanceof ForgeSpawnEggItem && Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(i)).getNamespace().equals(ShinyMob.MODID)) {
                getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(i)).getPath())
                        .parent(getExistingFile(new ResourceLocation("item/template_spawn_egg")));
            }
        }
    }
}