package net.biscuits310.redwoodforests.datagen;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.biscuits310.redwoodforests.block.ModBlocks;
import net.biscuits310.redwoodforests.datagen.custom.ModBlockModelGenerators;
import net.biscuits310.redwoodforests.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider
{
    public ModModelProvider(PackOutput output)
    {
        super(output, RedwoodForests.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels)
    {
        ModBlockModelGenerators modBlockModels = new ModBlockModelGenerators(
                blockModels.blockStateOutput,
                blockModels.itemModelOutput,
                blockModels.modelOutput
        );

        itemModels.generateFlatItem(ModItems.REDWOOD_BARK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CHARRED_REDWOOD_BARK.get(), ModelTemplates.FLAT_ITEM);

        //BLOCKS
        modBlockModels.createHorizontalVariatedLogBlock(ModBlocks.REDWOOD_LOG.get(), 3);
        modBlockModels.createVariatedWoodBlock(ModBlocks.REDWOOD_WOOD.get(), ModBlocks.REDWOOD_LOG.get(), 3);
        blockModels.woodProvider(ModBlocks.STRIPPED_REDWOOD_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_REDWOOD_LOG.get()).wood(ModBlocks.STRIPPED_REDWOOD_WOOD.get());

        blockModels.family(ModBlocks.REDWOOD_PLANKS.get())
                .stairs(ModBlocks.REDWOOD_STAIRS.get())
                .slab(ModBlocks.REDWOOD_SLAB.get())
                .fence(ModBlocks.REDWOOD_FENCE.get());
    }
}
