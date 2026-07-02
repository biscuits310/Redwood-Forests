package net.biscuits310.redwoodforests.datagen;

import net.biscuits310.redwoodforests.block.ModBlocks;
import net.biscuits310.redwoodforests.item.ModItems;
import net.biscuits310.redwoodforests.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;

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
                .requires(ModTags.Items.REDWOOD_LOGS)
                .unlockedBy(getHasName(ModBlocks.REDWOOD_LOG.get()), has(ModTags.Items.REDWOOD_LOGS))
                .group("redwood_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDWOOD_WOOD.get(), 3)
                .define('A', ModBlocks.REDWOOD_LOG.asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(ModBlocks.REDWOOD_LOG.get()), has(ModBlocks.REDWOOD_LOG))
                .group("redwood_wood")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_REDWOOD_WOOD.get(), 3)
                .define('A', ModBlocks.STRIPPED_REDWOOD_LOG.asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(ModBlocks.STRIPPED_REDWOOD_LOG.get()), has(ModBlocks.STRIPPED_REDWOOD_LOG))
                .group("stripped_redwood_wood")
                .save(output);

        stairBuilder(ModBlocks.REDWOOD_STAIRS.get(), Ingredient.of(ModBlocks.REDWOOD_STAIRS))
                .unlockedBy(getHasName(ModBlocks.REDWOOD_PLANKS.get()), has(ModBlocks.REDWOOD_PLANKS))
                .group("redwood_stairs")
                .save(output);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDWOOD_SLAB.get(), Ingredient.of(ModBlocks.REDWOOD_SLAB))
                .unlockedBy(getHasName(ModBlocks.REDWOOD_PLANKS.get()), has(ModBlocks.REDWOOD_PLANKS))
                .group("redwood_slab")
                .save(output);
    }
}
