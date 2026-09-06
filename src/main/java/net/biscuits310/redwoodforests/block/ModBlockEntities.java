package net.biscuits310.redwoodforests.block;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.biscuits310.redwoodforests.block.custom.RedwoodOriginBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, RedwoodForests.MODID);

    public static final Supplier<BlockEntityType<RedwoodOriginBlockEntity>> REDWOOD_ORIGIN_BLOCK = BLOCK_ENTITY_TYPES.register(
            "redwood_origin_block",
            () -> new BlockEntityType<>(
                    RedwoodOriginBlockEntity::new,
                    false,
                    ModBlocks.REDWOOD_ORIGIN_BLOCK.get()
            )
    );
}
