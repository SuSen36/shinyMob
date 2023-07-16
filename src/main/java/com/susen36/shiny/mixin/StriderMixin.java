package com.susen36.shiny.mixin;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Strider.class)
public abstract class StriderMixin extends Animal {
    protected StriderMixin(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }
    @Redirect(method = "finalizeSpawn", at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/monster/Strider;isBaby()Z"))
    public boolean finalizeSpawn(Strider instance) {
        return this.isBaby() ||this.getType()== EntityInit.SHINY_STRIDER.get();
    }
}
