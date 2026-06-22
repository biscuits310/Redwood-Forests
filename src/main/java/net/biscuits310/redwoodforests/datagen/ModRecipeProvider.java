package net.biscuits310.redwoodforests.datagen;

import net.biscuits310.redwoodforests.block.ModBlocks;
import net.biscuits310.redwoodforests.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner{
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "RedwoodForests Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDWOOD_PLANKS.get(),4)
                .requires(ModBlocks.REDWOOD_LOG)
                .unlockedBy(getHasName(ModBlocks.REDWOOD_LOG.get()), has(ModBlocks.REDWOOD_LOG))
                .group("redwood_planks")
                .save(output);
    }
}
