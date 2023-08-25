package com.github.jaquobia.worldwrap;

import net.minecraft.util.maths.MathHelper;

// https://stackoverflow.com/questions/707370/clean-efficient-algorithm-for-wrapping-integers-in-c
public class WrapperHelper {
    private static int ChunkMax = 0;
    private static int ChunkMin = 0;
    private static int ChunkRange = 1;
    private static int BlockMax = 0;
    private static int BlockMin = 0;
    private static int BlockRange = 16;
    private static void update_range() {
        ChunkRange = ChunkMax - ChunkMin + 1;
        BlockRange = ChunkRange << 4;
    }
    public static void set_max(int chunk_dimension_max) {
        ChunkMax = chunk_dimension_max;
        BlockMax = ChunkMax << 4;
        update_range();
    }
    public static void set_min(int chunk_dimension_min) {
        ChunkMin = chunk_dimension_min;
        BlockMin = ChunkMin << 4;
        update_range();
    }

    public static int wrap_chunk_axis(int axis) {
        if (axis < ChunkMin)
            axis += ChunkRange * (((ChunkMin - axis) / ChunkRange) + 1);

        return ChunkMin + ((axis - ChunkMin) % ChunkRange);
    }

    public static int wrap_block_axis(int axis) {
        if (axis < BlockMin)
            axis += BlockRange * (((BlockMin - axis) / BlockRange) + 1);

        return BlockMin + ((axis - BlockMin) % BlockRange);
    }

    public static double wrap_chunk_axis(double axis) {
        int i = MathHelper.floor(axis);
        int i_w = wrap_chunk_axis(i);
        return (axis - ((double)i)) + i_w;
    }

    public static double wrap_block_axis(double axis) {
        int i = MathHelper.floor(axis);
        int i_w = wrap_block_axis(i);
        return (axis - ((double)i)) + i_w;
    }

    public static int wrap_chunk_axis_min_0(int axis) {
        if (axis < ChunkMin) {
            final int ChunkMaxP1 = ChunkMax + 1;
            final int nAxis = (-axis / ChunkMaxP1) + 1;
            axis += ChunkMaxP1 * nAxis;
        }

        return axis % ChunkRange;
    }

    public static int wrap_block_axis_min_0(int axis) {
        if (axis < 0) {
            final int BlockMaxP1 = BlockMax + 1;
            final int nAxis = (-axis / BlockMaxP1) + 1;
            axis += BlockMaxP1 * nAxis;
        }

        return axis % BlockRange;
    }

    public static double get_block_range_f() {
        return BlockRange;
    }

    public static double squareDistanceBetween(double x, double y, double z, double x2, double y2, double z2) {
        double range = WrapperHelper.get_block_range_f();
        double dy = y - y2;
        dy = dy * dy;

        /// Ordinal * range is an approximation of how large dx/dz is allowed to be before range is subtracted from it
        /// Needs to be adjusted until 8 blocks of difference through the border of the wrapped world is the max
        double ordinal = 7.5/8.0;
        double dx = x - x2;
        if (dx > range * ordinal)
            dx -= range;
        dx = dx * dx;
        double dz = z - z2;
        if (dz > range * ordinal)
            dz-=range;
        dz = dz * dz;


        return dx + dy + dz;
    }
}
