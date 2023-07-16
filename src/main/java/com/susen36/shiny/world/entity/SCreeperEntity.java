package com.susen36.shiny.world.entity;

import com.google.common.collect.Lists;
import com.susen36.shiny.world.entity.ai.FireWorkGoal;
import com.susen36.shiny.world.entity.ai.KidnapAttackGoal;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class SCreeperEntity extends Creeper {
    public int life =0;

    public SCreeperEntity(EntityType<? extends Creeper> p_32278_, Level level) {
        super(p_32278_, level);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new KidnapAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new FireWorkGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }
    @Override
    public void tick() {
        if (this.isAlive()) {
            if (this.isOnFire()) {
                this.setSwellDir(1);
            }
            float lifetime = 47 * 2 + this.random.nextInt(2) + this.random.nextInt(2);

            if (this.swell >= this.maxSwell*0.75) {
                this.setDeltaMovement(this.random.triangle(0.0, 0.002297), 0.5, this.random.triangle(0.0, 0.002297));
                this.setYBodyRot(this.tickCount * 10);
                ++this.life;
                if (this.level().isClientSide && this.life % 5 < 2) {
                    this.level().addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), this.random.nextGaussian() * 0.05, -this.getDeltaMovement().y * 0.5, this.random.nextGaussian() * 0.05);
                }
                if (this.life > lifetime) {
                    if (this.level.isClientSide) {
                        Vec3 vec3 = this.getDeltaMovement();
                        if(!this.isPowered()) {
                            this.level().createFireworks(this.getX(), this.getY(), this.getZ(), vec3.x, vec3.y, vec3.z, generateTag(false));
                        }else {
                            this.level().createFireworks(this.getX(), this.getY(), this.getZ(), vec3.x, vec3.y, vec3.z, generateTag(true));
                        }
                        }
                    else {
                        this.remove(RemovalReason.KILLED);
                    }
                }
            }
        }
        super.tick();
    }

    private CompoundTag generateTag(boolean powered) {
        CompoundTag fireworkTag = new CompoundTag();
        CompoundTag fireworkItemTag = new CompoundTag();
        ListTag nbttaglist = new ListTag();
        List<Integer> list = Lists.<Integer>newArrayList();
        Random rand = new Random();
        list.add(0x0066FF);
        list.add(0xFFFF33);
        list.add(0x0FF00);
        list.add(0x0FFF00);
        list.add(0x33FF33);
        list.add(0xFF0000);
        for (int i = 0; i < rand.nextInt(6) + 6; i++)
            list.add(rand.nextInt(0x00ff00 + 1));
        int[] colours = new int[list.size()];
        for (int i = 0; i < colours.length; i++)
            colours[i] = list.get(i).intValue();
        fireworkTag.putIntArray("Colors", colours);
        fireworkTag.putBoolean("Flicker", true);
        fireworkTag.putByte("Type", (byte) (powered ? 3 : 4));
        nbttaglist.add((Tag) fireworkTag);
        fireworkItemTag.put("Explosions", nbttaglist);
        return fireworkItemTag;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

}
