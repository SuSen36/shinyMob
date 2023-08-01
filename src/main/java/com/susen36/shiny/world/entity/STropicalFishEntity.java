package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.ItemsInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class STropicalFishEntity extends AbstractSchoolingFish {
    public STropicalFishEntity(EntityType<? extends AbstractSchoolingFish> type, Level level) {
        super(type, level);
    }
    public ItemStack getBucketItemStack() {
        return new ItemStack(ItemsInit.SHINY_TROPICAL_FISH_BUCKET.get());
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.TROPICAL_FISH_FLOP;
    }
}
