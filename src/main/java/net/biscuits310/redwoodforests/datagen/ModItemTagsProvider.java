package net.biscuits310.redwoodforests.datagen;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.biscuits310.redwoodforests.block.ModBlocks;
import net.biscuits310.redwoodforests.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
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
                .add(ModBlocks.REDWOOD_LOG.asItem());

        tag(ItemTags.LOGS)
                .add(ModBlocks.REDWOOD_LOG.asItem());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.REDWOOD_LOG.asItem());
    }
}
