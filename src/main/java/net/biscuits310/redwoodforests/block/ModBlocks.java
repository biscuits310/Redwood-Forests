package net.biscuits310.redwoodforests.block;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.biscuits310.redwoodforests.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks
{
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(RedwoodForests.MODID);

    public static final DeferredBlock<Block> REDWOOD_PLANKS = registerBlock("redwood_planks",
            properties -> new Block(properties.strength(3f)
                    .mapColor(MapColor.WOOD)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()));
    public static final DeferredBlock<Block> REDWOOD_LOG = registerBlock("redwood_log",
            properties -> new RotatedPillarBlock(properties.strength(3f)
                    .mapColor(MapColor.WOOD)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()));
    public static final DeferredBlock<Block> STRIPPED_REDWOOD_LOG = registerBlock("stripped_redwood_log",
            properties -> new RotatedPillarBlock(properties.strength(3f)
                    .mapColor(MapColor.WOOD)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function)
    {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
