package com.susen36.shiny.world.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SSlimeEntity extends Slime {

    private int growUpTicks;
    protected final int maxSize=20;

    public SSlimeEntity(EntityType<? extends Slime> p_33588_, Level p_33589_) {
        super(p_33588_, p_33589_);
        this.refreshDimensions();
    }

    public void tick() {
        super.tick();
        this.noPhysics = false;
        this.setNoGravity(false);
        if (this.getSize() <= this.maxSize) {
            if (this.growUpTicks <= 200+this.getSize()*600) {
                this.growUpTicks++;
            } else {
                this.growUpTicks = 0;
                setSize(this.getSize() + 1, true);
            }
        }
    }
    @Override
    public void remove(Entity.RemovalReason removalReason) {
        this.setRemoved(removalReason);
        this.invalidateCaps();
        this.brain.clearMemories();
    }
    protected void dropEquipment() {
        super.dropEquipment();
        for(int i = 0; i< this.getSize(); ++i)
            this.spawnAtLocation(Items.SLIME_BALL);
    }
    protected void checkFallDamage(double p_19911_, boolean p_19912_, BlockState p_19913_, BlockPos p_19914_) {return;}
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.ATTACK_DAMAGE, 1.0D).add(Attributes.MOVEMENT_SPEED, 0.15F).add(Attributes.FOLLOW_RANGE, 32D);
    }
}
