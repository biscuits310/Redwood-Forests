package net.biscuits310.redwoodforests.datagen;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.biscuits310.redwoodforests.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, RedwoodForests.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.SNAPS_GOAT_HORN)
                .add(ModBlocks.REDWOOD_LOG.get());

        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(ModBlocks.REDWOOD_LOG.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.REDWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.get());

        tag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)
                .add(ModBlocks.REDWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.get());

        tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
                .add(ModBlocks.REDWOOD_LOG.get());

        tag(BlockTags.PARROTS_SPAWNABLE_ON)
                .add(ModBlocks.REDWOOD_LOG.get());

        tag(BlockTags.LOGS)
                .add(ModBlocks.REDWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.REDWOOD_LOG.get())
                .add(ModBlocks.REDWOOD_PLANKS.get())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.REDWOOD_PLANKS.get());

        tag(Tags.Blocks.STRIPPED_LOGS)
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.get());

        tag(Tags.Blocks.NATURAL_LOGS)
                .add(ModBlocks.REDWOOD_LOG.get());

        tag(Tags.Blocks.OVERWORLD_NATURAL_LOGS)
                .add(ModBlocks.REDWOOD_LOG.get());
    }
}
