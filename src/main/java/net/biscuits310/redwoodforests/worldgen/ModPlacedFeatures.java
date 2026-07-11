package net.biscuits310.redwoodforests.worldgen;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

// Creates placed features
// Placed features are configured features given positions
public class ModPlacedFeatures {

    // Actions to be executed
    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        // Get the configured features
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
    }

    // Register a placed feature key
    private static ResourceKey<PlacedFeature> registerKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(RedwoodForests.MODID, name));
    }

    // Used to add features to a key
    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers){
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
