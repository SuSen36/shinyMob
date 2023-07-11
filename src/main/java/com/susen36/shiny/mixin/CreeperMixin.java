package com.susen36.shiny.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FireworkParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collections;
import java.util.Random;

@Mixin(Creeper.class)
public abstract class CreeperMixin extends Monster{
    @Shadow private int swell;

    @Shadow private int oldSwell;

    @Shadow public abstract void thunderHit(ServerLevel p_32286_, LightningBolt p_32287_);

    @Shadow public abstract boolean isIgnited();

    @Shadow public abstract void setSwellDir(int p_32284_);

    @Shadow public abstract int getSwellDir();

    @Shadow private int maxSwell;

    @Shadow public abstract boolean isPowered();

    @Shadow public abstract void ignite();

    protected CreeperMixin(EntityType<? extends Monster> type, Level level) {
        super(type, level);
         }
    private int life =0;
    /**
     * @author SuSen36
     * @reason
     */
    @Overwrite
    public void tick() {
        if (this.isAlive()) {
            this.oldSwell = this.swell;
            if (this.isIgnited()) {
                this.setSwellDir(1);
            }

            int i = this.getSwellDir();
            if (i > 0 && this.swell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }

            this.swell += i;
            if (this.swell < 0) {
                this.swell = 0;
            }
            float lifetime = 47 * 2 + this.random.nextInt(2) + this.random.nextInt(2);

            if (this.swell >= this.maxSwell) {
                this.swell = this.maxSwell;
                this.setDeltaMovement(this.random.triangle(0.0, 0.002297), 0.5, this.random.triangle(0.0, 0.002297));
                this.setYBodyRot(this.tickCount * 10);
                ++this.life;
                this.ignite();
                if (this.level().isClientSide && this.life % 5 < 2) {
                    this.level().addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), this.random.nextGaussian() * 0.05, -this.getDeltaMovement().y * 0.5, this.random.nextGaussian() * 0.05);
                }
                if (this.life > lifetime) {
                    if (this.level.isClientSide) {
                        spawnParticles();
                    }else{
                    this.dead = true;
                    this.discard();
                }
            }

        }
      }
        super.tick();
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnParticles() {
        Minecraft.getInstance().particleEngine
                .add(new FireworkParticles.Starter((ClientLevel)this.level, this.position().x, this.position().y + 0.5F,
                        this.position().z, 0, 0, 0, Minecraft.getInstance().particleEngine, generateTag(false)));
        if (this.isPowered())
            Minecraft.getInstance().particleEngine
                    .add(new FireworkParticles.Starter((ClientLevel)this.level, this.position().x, this.position().y + 2.5F,
                            this.position().z, 0, 0, 0, Minecraft.getInstance().particleEngine, generateTag(true)));
    }
    private CompoundTag generateTag(boolean powered) {
        CompoundTag fireworkTag = new CompoundTag();
        CompoundTag fireworkItemTag = new CompoundTag();
        ListTag nbttaglist = new ListTag();
        Random rand = new Random();

        int colours = (rand.nextInt(0xffffff +1));

        fireworkTag.putIntArray("Colors", Collections.singletonList(colours));
        fireworkTag.putBoolean("Flicker", false);
        fireworkTag.putByte("Type", (byte) (powered ? 3 : 4));
        fireworkTag.put("Explosions", nbttaglist);
        nbttaglist.add((Tag) fireworkTag);
        return fireworkItemTag;
    }
}
