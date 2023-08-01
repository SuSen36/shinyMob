package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SSnifferEntity extends Sniffer {
    public SSnifferEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    public void dropSeed() {
        if (!this.level().isClientSide()&& this.entityData.get(DATA_DROP_SEED_AT_TICK) == this.tickCount) {
            ServerLevel serverlevel = (ServerLevel) this.level();
            BlockPos blockpos = this.getHeadBlock();
            int amount = this.random.nextInt(4)+1;
            for (int i = 0; i < amount; i++) {
                ItemStack itemstack = Items.POTATO.getDefaultInstance();
                ItemEntity itementity = new ItemEntity(serverlevel, (double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ(), itemstack);
                itementity.setDefaultPickUpDelay();
                serverlevel.addFreshEntity(itementity);
                this.playSound(SoundEvents.SNIFFER_DROP_SEED, 1.0F, 1.0F);
            }
        }
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob p_146744_) {
        return EntityInit.SHINY_SNIFFER.get().create(level);
    }
}
