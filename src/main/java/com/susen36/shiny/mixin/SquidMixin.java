package com.susen36.shiny.mixin;

import com.susen36.shiny.world.entity.SGlowSquidEntity;
import com.susen36.shiny.world.entity.SSquidEntity;
import net.minecraft.world.entity.animal.Squid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Squid.class)
public  abstract class SquidMixin {
    @Shadow protected abstract void spawnInk();

    @Redirect(method = "hurt", at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/animal/Squid;spawnInk()V"))
    public void hurt(Squid instance) {
        if (!(instance instanceof SSquidEntity)&&!(instance instanceof SGlowSquidEntity)) {
            this.spawnInk();
        }
    }
}
