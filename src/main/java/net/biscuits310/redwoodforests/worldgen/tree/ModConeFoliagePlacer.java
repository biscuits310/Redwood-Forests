package net.biscuits310.redwoodforests.worldgen.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class ModConeFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<ModConeFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            i -> foliagePlacerParts(i).and(IntProviders.codec(0, 24).fieldOf("crown_height").forGetter(p -> p.crownHeight)).apply(i, ModConeFoliagePlacer::new));

    private final IntProvider crownHeight;

    public ModConeFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider crownHeight){
        super(radius, offset);
        this.crownHeight = crownHeight;
    }

    @Override
    protected FoliagePlacerType type(){
        return ModFoliagePlacerType.CONE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(
            WorldGenLevel level,
            FoliageSetter foliageSetter,
            RandomSource random,
            TreeConfiguration config,
            int treeHeight,
            FoliageAttachment foliageAttachment,
            int foliageHeight,
            int leafRadius,
            int offset
    ) {
        BlockPos foliagePos = foliageAttachment.pos();
        int currentRadius;

        for (int depth = 1; depth <= foliageHeight; depth++){
            currentRadius = Mth.floor(depth * leafRadius / (float)foliageHeight);
            this.placeLeavesRow(level, foliageSetter, random, config, foliagePos, currentRadius, -depth+offset, foliageAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration config) {
        return this.crownHeight.sample(random);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int currentRadius, boolean doubleTrunk) {
        return dx*dx + dz*dz > currentRadius*currentRadius;
    }
}
