package net.biscuits310.redwoodforests.datagen.custom;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModBlockModelGenerators extends BlockModelGenerators {
    public ModBlockModelGenerators(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    public static MultiVariant createRandomVariants(Variant... variants){
        return variants(variants);
    }

    public void createVariatedLogBLock(Block block, int numSides) {
        Variant[] variants = new Variant[numSides];
        for (int i = 1; i <= numSides; i++){
            variants[i-1] = plainModel(ModelTemplates.CUBE_COLUMN.create(
                    Identifier.fromNamespaceAndPath(RedwoodForests.MODID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + "_" + i),
                    ModTextureMapping.randomLogColumn(block, i),
                    ModBlockModelGenerators.this.modelOutput));
        }
        this.blockStateOutput.accept(createAxisAlignedPillarBlock(block, createRandomVariants(variants)));
        this.registerSimpleItemModel(block, Identifier.fromNamespaceAndPath(RedwoodForests.MODID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + "_1"));
    }

    public void createHorizontalVariatedLogBlock(Block block, int numSides){
        Variant[] variants = new Variant[numSides];
        Variant[] horizontalVariants = new Variant[numSides];
        for (int i = 1; i <= numSides; i++){
            variants[i-1] = plainModel(ModelTemplates.CUBE_COLUMN.create(
                    Identifier.fromNamespaceAndPath(RedwoodForests.MODID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + "_" + i),
                    ModTextureMapping.randomLogColumn(block, i),
                    ModBlockModelGenerators.this.modelOutput));
            horizontalVariants[i-1] = plainModel(ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(
                    Identifier.fromNamespaceAndPath(RedwoodForests.MODID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + "_horizontal_" + i),
                    ModTextureMapping.randomLogColumn(block, i),
                    ModBlockModelGenerators.this.modelOutput));
        }
        this.blockStateOutput.accept(createRotatedPillarWithHorizontalVariant(block, createRandomVariants(variants), createRandomVariants(horizontalVariants)));
        this.registerSimpleItemModel(block, Identifier.fromNamespaceAndPath(RedwoodForests.MODID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + "_1"));
    }

    public void createVariatedWoodBlock(Block block, Block logBlock, int numSides){
        Variant[] variants = new Variant[numSides];
        for (int i = 1; i <= numSides; i++){
            variants[i-1] = plainModel(ModelTemplates.CUBE_COLUMN.create(
                    Identifier.fromNamespaceAndPath(RedwoodForests.MODID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + "_" + i),
                    ModTextureMapping.randomLogColumn(logBlock, i).copyAndUpdate(TextureSlot.END, ModTextureMapping.randomLogColumn(logBlock, i).get(TextureSlot.SIDE)),
                    ModBlockModelGenerators.this.modelOutput));
        }
        this.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(block, createRandomVariants(variants)));
        this.registerSimpleItemModel(block, Identifier.fromNamespaceAndPath(RedwoodForests.MODID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + "_1"));
    }
}
