package com.susen36.shiny.mixin;

import com.susen36.shiny.world.entity.SCreeperEntity;
import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Creeper.class)
public abstract class CreeperMixin{

    @Shadow protected abstract void explodeCreeper();

    @Redirect(method = "tick", at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/monster/Creeper;explodeCreeper()V"))
    public void tick(Creeper instance) {
        if(!(instance instanceof SCreeperEntity)){
            this.explodeCreeper();
        }
    }

}
