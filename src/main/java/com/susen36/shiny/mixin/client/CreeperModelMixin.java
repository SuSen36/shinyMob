package com.susen36.shiny.mixin.client;

import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperModel.class)
public abstract class CreeperModelMixin <T extends Entity> extends HierarchicalModel<T> {
    @Shadow @Final private ModelPart head;

    @Shadow public abstract ModelPart root();

    @Shadow @Final private ModelPart root;

    @Inject(at = @At(value = "HEAD"), method = "setupAnim", cancellable = true)
    private void setupAnim(T entity, float p_102464_, float p_102465_, float p_102466_, float p_102467_, float p_102468_, CallbackInfo ci) {
        this.setupAnim( entity.tickCount, p_102464_, p_102465_, p_102466_, p_102467_, p_102468_);
    }
    private void setupAnim( int p_103243_, float p_103244_, float p_103245_, float p_103246_, float p_103247_, float p_103248_) {
                float f = Mth.cos((float)p_103243_);
                float f1 = Mth.sin((float)p_103243_);
                this.head.x = f;
                this.head.y = 8+f1;
                this.head.zRot = Mth.sin((float)p_103243_) * 0.4F;
                this.root().getChild("body").x = f;
                this.root().getChild("body").y = 7+f1;
                
    }

}

