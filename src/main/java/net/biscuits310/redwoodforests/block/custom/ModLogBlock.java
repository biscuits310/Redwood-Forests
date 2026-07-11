package net.biscuits310.redwoodforests.block.custom;

import net.biscuits310.redwoodforests.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
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

// Creates a custom flammable, strippable, rotatable pillar block blueprint for DeferredBlocks
public class ModLogBlock extends RotatedPillarBlock {
    // Initialises a Block supplier called strippedBlock
    private final Supplier<Block> strippedBlock;

    // Constructs everything required by the inherited class
    // Assigns the given strippedBlock parameter to the strippedBlock supplier
    public ModLogBlock(Properties properties, Supplier<Block> strippedBlock) {
        super(properties);
        this.strippedBlock = strippedBlock;
    }

    // Returns the strippedBlock
    public Block getStrippedBlock() {
        return strippedBlock.get();
    }

    // Replaces the log block with strippedBlock when right clicked with an axe item
    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        // If the currently held item has the axe item tag
        if (itemStack.is(ItemTags.AXES)){
            // Store the old unstripped block's state (its axis)
            BlockState oldState = level.getBlockState(pos);
            // Store the new state as StrippedBlock with the old axis
            BlockState newState = getStrippedBlock()
                            .defaultBlockState()
                                    .setValue(RotatedPillarBlock.AXIS, oldState.getValue(RotatedPillarBlock.AXIS));

            // Play the axe stripping sound
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1f, 1f);
            // Only execute on the server
            if(!level.isClientSide()){
                // Set the block as the stored stripped block with the correct axis
                level.setBlock(pos, newState, 3);
            }
            // Return a success, which lets the click animation play
            return InteractionResult.SUCCESS;
        }
        // Return a pass, which stops any animations from playing
        return InteractionResult.PASS;
    }

    // Set flammability to true
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    // Set the flammability to 5
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    // Set the speed fire spreads to 20
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
    }
}
