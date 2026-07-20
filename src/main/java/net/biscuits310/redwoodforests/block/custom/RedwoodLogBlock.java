package net.biscuits310.redwoodforests.block.custom;

import net.biscuits310.redwoodforests.block.ModBlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.function.Supplier;

public class RedwoodLogBlock extends StrippableFlammableRotatedPillarBlock{
    public static final BooleanProperty PREVENTS_NEARBY_LEAF_DECAY = ModBlockStateProperties.PREVENTS_NEARBY_LEAF_DECAY;

    public RedwoodLogBlock(int flammability, int fireSpreadSpeed, Supplier<Block> strippedBlock, Properties properties) {
        super(flammability, fireSpreadSpeed, strippedBlock, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PREVENTS_NEARBY_LEAF_DECAY, true).setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PREVENTS_NEARBY_LEAF_DECAY);
    }
}
