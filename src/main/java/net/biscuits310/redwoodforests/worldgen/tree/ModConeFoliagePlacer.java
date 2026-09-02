package net.biscuits310.redwoodforests.worldgen.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.biscuits310.redwoodforests.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.HashSet;
import java.util.Set;

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
        Set<BlockPos> leafBlocks = new HashSet<>();

        for (int depth = 1; depth <= foliageHeight; depth++){
            currentRadius = Math.round(depth * leafRadius / (float)foliageHeight);
            this.placeLeavesRow(level, foliageSetter, random, config, foliagePos, currentRadius, -depth+offset, foliageAttachment.doubleTrunk(), leafBlocks);
        }

        for (int height = 0; height <=2; height++){
            tryPlaceLeaf(level, foliageSetter, random, config, foliageAttachment.pos().offset(0, height, 0));
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration config) {
        return this.crownHeight.sample(random);
    }

    protected void placeLeavesRow(WorldGenLevel level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, BlockPos origin, int currentRadius, int y, boolean doubleTrunk, Set<BlockPos> leafBlocks) {
        int offset = doubleTrunk ? 1 : 0;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int dx = -currentRadius; dx <= currentRadius + offset; dx++) {
            for (int dz = -currentRadius; dz <= currentRadius + offset; dz++) {
                BlockPos rootPos = origin.offset(dx, 0, dz).atY(y);

                if (!this.shouldSkipLocationSigned(random, dx, y, dz, currentRadius, doubleTrunk) &&
                        !shouldSkipLocationLight(dx, dz, random, leafBlocks, rootPos, currentRadius)
                ) {
                    leafBlocks.add(rootPos);
                    pos.setWithOffset(origin, dx, y, dz);
                    tryPlaceLeaf(level, foliageSetter, random, config, pos);
                }
            }
        }
    }

    protected boolean shouldSkipLocationLight(int dx, int dz, RandomSource random, Set<BlockPos> leafBlocks, BlockPos rootPos, int currentRadius) {
        float skipChance = -0.1f;

        float distance = Mth.sqrt(dx*dx + dz*dz);
        skipChance += distance / currentRadius * 0.3;

        if (leafBlocks.contains(rootPos.west())) {skipChance+=0.15;}
        if (leafBlocks.contains(rootPos.above())) {skipChance+=0.15;}
        if (leafBlocks.contains(rootPos.north())) {skipChance+=0.15;}

        if (skipChance > 1){skipChance = 1;}
        if (skipChance < 0){skipChance = 0;}

        if (random.nextFloat() < skipChance){
            return true;
        }

        return false;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int currentRadius, boolean doubleTrunk) {
        return dx*dx + dz*dz > currentRadius*currentRadius;
    }
}
