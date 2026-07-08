package net.biscuits310.redwoodforests.worldgen.tree;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.biscuits310.redwoodforests.worldgen.ModConfiguredFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower REDWOOD = new TreeGrower(
            RedwoodForests.MODID + "redwood",
            0.0F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.REDWOOD_KEY),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
}
