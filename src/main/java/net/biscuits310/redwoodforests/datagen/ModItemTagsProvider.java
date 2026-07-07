package net.biscuits310.redwoodforests.datagen;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.biscuits310.redwoodforests.block.ModBlocks;
import net.biscuits310.redwoodforests.item.ModItems;
import net.biscuits310.redwoodforests.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, RedwoodForests.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.PLANKS)
                .add(ModBlocks.REDWOOD_PLANKS.asItem());

        tag(ItemTags.WOODEN_TOOL_MATERIALS)
                .add(ModBlocks.REDWOOD_PLANKS.asItem());

        tag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL)
                .add(ModBlocks.REDWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_REDWOOD_WOOD.asItem())
                .add(ModBlocks.REDWOOD_LEAVES.asItem());

        tag(ItemTags.LOGS)
                .add(ModBlocks.REDWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_REDWOOD_WOOD.asItem());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.REDWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_REDWOOD_WOOD.asItem());

        tag(ItemTags.STAIRS)
                .add(ModBlocks.REDWOOD_STAIRS.asItem());

        tag(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.REDWOOD_STAIRS.asItem());

        tag(ItemTags.SLABS)
                .add(ModBlocks.REDWOOD_SLAB.asItem());

        tag(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.REDWOOD_SLAB.asItem());

        tag(ItemTags.FENCES)
                .add(ModBlocks.REDWOOD_FENCE.asItem());

        tag(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.REDWOOD_FENCE.asItem());

        tag(ItemTags.LEAVES)
                .add(ModBlocks.REDWOOD_LEAVES.asItem());

        tag(Tags.Items.STRIPPED_LOGS)
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.asItem());

        tag(Tags.Items.STRIPPED_WOODS)
                .add(ModBlocks.STRIPPED_REDWOOD_WOOD.asItem());

        tag(Tags.Items.FENCES)
                .add(ModBlocks.REDWOOD_FENCE.asItem());

        tag(Tags.Items.FENCES_WOODEN)
                .add(ModBlocks.REDWOOD_FENCE.asItem());

        tag(ModTags.Items.REDWOOD_LOGS)
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.asItem())
                .add(ModBlocks.REDWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_REDWOOD_WOOD.asItem());
    }
}
