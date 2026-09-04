package net.biscuits310.redwoodforests.entity.block;

import net.biscuits310.redwoodforests.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class RedwoodOriginBlockEntity extends BlockEntity {
    private int seed = 0;

    public RedwoodOriginBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.REDWOOD_ORIGIN_BLOCK.get(), worldPosition, blockState);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("GrowthSeed", this.seed);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.seed = input.getIntOr("GrowthSeed", 0);
    }

    public int getSeed() {return this.seed;}

}
