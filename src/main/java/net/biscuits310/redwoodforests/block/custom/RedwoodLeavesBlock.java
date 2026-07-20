package net.biscuits310.redwoodforests.block.custom;

import net.biscuits310.redwoodforests.block.ModBlockStateProperties;
import net.biscuits310.redwoodforests.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.OptionalInt;

public class RedwoodLeavesBlock extends FlammableUntintedParticleLeavesBlock {

    public RedwoodLeavesBlock(float leafParticleChance, ParticleOptions leafParticle, int flammability, int fireSpreadSpeed, Properties properties) {
        super(leafParticleChance, leafParticle, flammability, fireSpreadSpeed, properties);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random){
        level.setBlock(pos,updateDistanceWithDiagonal(state, level, pos), 3);
    }

    private static BlockState updateDistanceWithDiagonal(BlockState state, LevelAccessor level, BlockPos pos) {
        int newDistance = 7;
        BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();

        for (int dx = -1; dx <= 1; dx++){
            for (int dy = -1; dy <= 1; dy++){
                for (int dz = -1; dz <= 1; dz++){
                    if (dx == 0 && dy == 0 && dz == 0){
                        continue;
                    }

                    neighborPos.setWithOffset(pos, dx, dy, dz);
                    newDistance = Math.min(newDistance, getBlockStateDistanceAt(level.getBlockState(neighborPos)) + 1);
                    if (newDistance == 1){
                        return state.setValue(DISTANCE, 1);
                    }
                }
            }
        }

        return state.setValue(DISTANCE, newDistance);
    }

    private static int getBlockStateDistanceAt (BlockState state) {
        return getOptionalDistanceBlockstate(state).orElse(7);
    }

    public static OptionalInt getOptionalDistanceBlockstate(BlockState state){
        if (state.is(ModBlocks.REDWOOD_LOG)){
            if (state.getValue(ModBlockStateProperties.PREVENTS_NEARBY_LEAF_DECAY)){
                return OptionalInt.of(0);
            }
            else {
                return state.hasProperty(DISTANCE) ? OptionalInt.of(state.getValue(DISTANCE)) : OptionalInt.empty();
            }
        }
        else {
            if (state.is(BlockTags.PREVENTS_NEARBY_LEAF_DECAY)){
                return  OptionalInt.of(0);
            }
            else {
                return state.hasProperty(DISTANCE) ? OptionalInt.of(state.getValue(DISTANCE)) : OptionalInt.empty();
            }
        }
    }
}
