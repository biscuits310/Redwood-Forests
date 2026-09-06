package net.biscuits310.redwoodforests.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.RandomSupport;

public class RepeatableSaplingBlock extends SaplingBlock {
    public RepeatableSaplingBlock(TreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
    }

    @Override
    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        long randomSeed = RandomSupport.generateUniqueSeed();
        random.setSeed(randomSeed);
        super.advanceTree(level, pos, state, random);
        if (level.getBlockEntity(pos) instanceof RedwoodOriginBlockEntity redwoodOriginBlockEntity)
            redwoodOriginBlockEntity.setSeed(randomSeed);
    }
}
