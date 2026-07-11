package net.biscuits310.redwoodforests.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FlammableUntintedParticleLeavesBlock extends UntintedParticleLeavesBlock {
    int flammability;
    int fireSpreadSpeed;
    public FlammableUntintedParticleLeavesBlock(float leafParticleChance, ParticleOptions leafParticle, int flammability, int fireSpreadSpeed, Properties properties) {
        super(leafParticleChance, leafParticle, properties);
        this.flammability = flammability;
        this.fireSpreadSpeed = fireSpreadSpeed;
    }

    // Set flammability to true
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    // Set the flammability to the given flammability
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.flammability;
    }

    // Set the speed fire spreads to the given fireSpreadSpeed
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.fireSpreadSpeed;
    }
}
