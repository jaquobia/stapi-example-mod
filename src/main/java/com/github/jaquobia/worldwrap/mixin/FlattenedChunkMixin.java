package com.github.jaquobia.worldwrap.mixin;

import com.github.jaquobia.worldwrap.WrapperHelper;
import net.minecraft.entity.EntityBase;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.impl.level.chunk.FlattenedChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FlattenedChunk.class)
public class FlattenedChunkMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/maths/MathHelper;floor(D)I", ordinal = 0), method = "addEntity", remap = false)
    public int redirectN2(double d) {
        return WrapperHelper.wrap_chunk_axis(MathHelper.floor(d));
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/maths/MathHelper;floor(D)I", ordinal = 1), method = "addEntity")
    public int redirectN3(double d) {
        return WrapperHelper.wrap_chunk_axis(MathHelper.floor(d));
    }

    @Inject(at = @At(value = "TAIL"), method = "addEntity", locals = LocalCapture.CAPTURE_FAILSOFT, remap = false)
    public void injectDebugAddEntity(EntityBase entity, CallbackInfo ci, int n2, int n3) {
        double x = WrapperHelper.wrap_block_axis(entity.x);
        double z = WrapperHelper.wrap_block_axis(entity.z);
        entity.setPosition(x, entity.y, z);
    }


}
