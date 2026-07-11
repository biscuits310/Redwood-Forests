package net.biscuits310.redwoodforests.datagen;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.biscuits310.redwoodforests.worldgen.ModBiomeModifiers;
import net.biscuits310.redwoodforests.worldgen.ModConfiguredFeatures;
import net.biscuits310.redwoodforests.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

// Creates a set of registries
public class ModDataPackProvider extends DatapackBuiltinEntriesProvider {
    // Creates a registry set using the different registries while marking the boostrap
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            // Adds the configured feature registry
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            // Adds the placed feature registry
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            // Adds the biome modifiers registry
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::boostrap);

    // Constructs everything required by the inherited class
    public ModDataPackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries){
        super(output, registries, BUILDER, Set.of(RedwoodForests.MODID));
    }
}
