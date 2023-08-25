package com.github.jaquobia.worldwrap.mixin;

import com.github.jaquobia.worldwrap.WrapperHelper;
import net.minecraft.level.chunk.Chunk;
import net.minecraft.level.chunk.ServerChunkCache;
import net.minecraft.util.maths.Vec2i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(ServerChunkCache.class)
public abstract class ServerChunkCacheMixin {
    @Shadow private Map serverChunkCache;

    @Shadow public abstract Chunk loadChunk(int i, int j);
    @Unique private static boolean flag_wrap_set_min_max = true;

    @Inject(at = @At(value = "TAIL"), method = "getChunk", locals = LocalCapture.CAPTURE_FAILSOFT, remap = false, cancellable = true)
    public void injectGetChunk(int i, int j, CallbackInfoReturnable<Chunk> cir, Chunk var3) {
        final int max = 15;
        final int min = 0;
        if (flag_wrap_set_min_max) {
            WrapperHelper.set_min(min);
            WrapperHelper.set_max(max);
            flag_wrap_set_min_max = false;
        }

        i = WrapperHelper.wrap_chunk_axis(i);
        j = WrapperHelper.wrap_chunk_axis(j);

        var3 = (Chunk) this.serverChunkCache.get(Vec2i.hash(i, j));
        cir.setReturnValue(var3 == null ? this.loadChunk(i, j) : var3);
    }
}
