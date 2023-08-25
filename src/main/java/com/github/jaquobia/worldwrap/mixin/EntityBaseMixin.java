package com.github.jaquobia.worldwrap.mixin;

import com.github.jaquobia.worldwrap.WrapperHelper;
import net.minecraft.entity.EntityBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityBase.class)
public class EntityBaseMixin {
    @Shadow public double x;

    @Shadow public double y;

    @Shadow public double z;

    @Inject(at = @At("HEAD"), method = "squaredDistanceTo", cancellable = true, remap = false)
    public void injectSquaredDistanceTo(double d, double e, double f, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(WrapperHelper.squareDistanceBetween(x, y, z, d, e, f));
    }
}
