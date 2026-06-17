package net.biscuits310.redwoodforests.datagen;

import net.biscuits310.redwoodforests.RedwoodForests;
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
        itemModels.generateFlatItem(ModItems.REDWOOD_BARK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CHARRED_REDWOOD_BARK.get(), ModelTemplates.FLAT_ITEM);
    }
}
