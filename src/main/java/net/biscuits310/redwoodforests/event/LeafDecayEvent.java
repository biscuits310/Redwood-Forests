package net.biscuits310.redwoodforests.event;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.biscuits310.redwoodforests.block.custom.RedwoodLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = RedwoodForests.MODID)
public class LeafDecayEvent {

    public static void tickDiagonalRedwoodLeaves(ServerLevel level, BlockPos pos){
        for (int dx = -1; dx <= 1; dx++){
            for (int dy = -1; dy <= 1; dy++){
                for (int dz = -1; dz <= 1; dz++){
                    int adjacency = 0;
                    if (dx == 0) {adjacency++;}
                    if (dy == 0) {adjacency++;}
                    if (dz == 0) {adjacency++;}
                    if (adjacency >= 2) {continue;}

                    BlockPos blockUpdatePos = pos.offset(dx, dy, dz);
                    BlockState blockUpdateState = level.getBlockState(blockUpdatePos);
                    if (blockUpdateState.getBlock() instanceof RedwoodLeavesBlock){
                        level.scheduleTick(blockUpdatePos, blockUpdateState.getBlock(), 1);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event){
        if (!(event.getLevel() instanceof ServerLevel level)){
            return;
        }

        if (!(event.getState().is(BlockTags.PREVENTS_NEARBY_LEAF_DECAY))){
            return;
        }

        tickDiagonalRedwoodLeaves(level, event.getPos());
    }

    @SubscribeEvent
    public static void onNeighbourUpdates(BlockEvent.NeighborNotifyEvent event){
        if (!(event.getLevel() instanceof ServerLevel level)){
            return;
        }

        if (!(event.getState().is(BlockTags.LEAVES))){
            return;
        }

        tickDiagonalRedwoodLeaves(level, event.getPos());
    }
}
