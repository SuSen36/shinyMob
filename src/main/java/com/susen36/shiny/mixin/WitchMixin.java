package com.susen36.shiny.mixin;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Witch.class)
public abstract class WitchMixin extends Mob {
    protected WitchMixin(EntityType<? extends Mob> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/monster/Witch;isAlive()Z"))
    public boolean banAiStep(Witch instance) {
        return this.isAlive() && !(this.getType() == EntityInit.SHINY_WITCH.get());
    }
}
