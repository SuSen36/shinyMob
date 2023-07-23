package com.susen36.shiny.world.entity;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class SBatEntity extends Bat {
    public SBatEntity(EntityType<? extends Bat> type, Level level) {
        super(type, level);
        this.xpReward=3;
    }
    protected void dropEquipment() {
        super.dropEquipment();
        if(this.random.nextInt(6)<=4) {
            this.spawnAtLocation(this.getDropItem(this.random));
        }
    }
    @Nonnull
    public Item getDropItem(RandomSource randomSource) {
        int i = randomSource.nextInt(100);
        if (i < 15) {
            return Items.LEATHER;
        } else if (i < 30) {
            return Items.COAL;
        } else if (i < 45) {
            return Items.IRON_ORE;
        } else if (i < 58) {
            return Items.GOLD_ORE;
        } else {
            return randomSource.nextInt(100) == 0 ? Items.DIAMOND : Items.LEATHER;
        }
    }

}
