package net.biscuits310.redwoodforests.datagen;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class ModTextureMapping extends TextureMapping {
    public static TextureMapping randomLogColumn(Block block, int i){
        return new TextureMapping()
                .put(TextureSlot.SIDE, getBlockTexture(block, "_" + i))
                .put(TextureSlot.END, getBlockTexture(block, "_top"))
                .put(TextureSlot.PARTICLE, getBlockTexture(block));
    }
}
