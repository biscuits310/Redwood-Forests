package net.biscuits310.redwoodforests.block.custom;

import net.biscuits310.redwoodforests.block.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jspecify.annotations.Nullable;

import java.util.function.Supplier;

public class RedwoodOriginBlock extends RedwoodLogBlock implements EntityBlock {
    public static final IntegerProperty GROWTH_STAGE = ModBlockStateProperties.GROWTH_STAGE;

    public RedwoodOriginBlock(int flammability, int fireSpreadSpeed, Supplier<Block> strippedBlock, Properties properties) {
        super(flammability, fireSpreadSpeed, strippedBlock, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PREVENTS_NEARBY_LEAF_DECAY, true).setValue(NATURAL_LOG, true).setValue(AXIS, Direction.Axis.Y).setValue(GROWTH_STAGE, 0));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new RedwoodOriginBlockEntity(worldPosition, blockState);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return;
        if (random.nextInt(7) == 0 && state.getValue(GROWTH_STAGE) <= 3 && state.getValue(PREVENTS_NEARBY_LEAF_DECAY)){
            this.advanceTree(level, pos, state, random);
        }
    }

    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random){
        int growthStage = state.getValue(GROWTH_STAGE);
        if (growthStage == 0){
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(GROWTH_STAGE);
    }
}
