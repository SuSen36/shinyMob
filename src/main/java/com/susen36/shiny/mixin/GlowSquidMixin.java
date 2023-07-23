package com.susen36.shiny.mixin;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GlowSquid.class)
public abstract class GlowSquidMixin extends Squid {


    public GlowSquidMixin(EntityType<? extends Squid> p_29953_, Level p_29954_) {
        super(p_29953_, p_29954_);
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE",target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public void aiStep(Level instance, ParticleOptions p_46631_, double p_46632_, double p_46633_, double p_46634_, double p_46635_, double p_46636_, double p_46637_) {
        if (!(this.getType()== EntityInit.SHINY_GLOW_SQUID.get())) {
            this.level().addParticle(ParticleTypes.GLOW, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
        }
    }
}
