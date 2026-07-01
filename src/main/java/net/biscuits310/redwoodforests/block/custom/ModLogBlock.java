package net.biscuits310.redwoodforests.block.custom;

import net.biscuits310.redwoodforests.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.function.Supplier;

public class ModLogBlock extends RotatedPillarBlock {
    private final Supplier<Block> strippedBlock;

    public ModLogBlock(Properties properties, Supplier<Block> strippedBlock) {
        super(properties);
        this.strippedBlock = strippedBlock;
    }

    public Block getStrippedBlock() {
        return strippedBlock.get();
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.is(ItemTags.AXES)){
            BlockState oldState = level.getBlockState(pos);
            BlockState newState = getStrippedBlock()
                            .defaultBlockState()
                                    .setValue(RotatedPillarBlock.AXIS, oldState.getValue(RotatedPillarBlock.AXIS));

            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1f, 1f);
            if(!level.isClientSide()){
                level.setBlock(pos, newState, 3);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }


}
