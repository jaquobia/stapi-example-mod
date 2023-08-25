package com.github.jaquobia.worldwrap.mixin;

import net.minecraft.level.chunk.Chunk;
import net.minecraft.level.chunk.ChunkCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkCache.class)
public class ChunkCacheMixin {
    @Inject(at = @At("TAIL"), method = "getChunk")
    public void injectGetChunk(int j, int par2, CallbackInfoReturnable<Chunk> cir) {
        System.out.printf("Client? Chunk Pos %d, %d \n", j, par2);
    }
}
