package net.biscuits310.redwoodforests.creativemodetab;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.biscuits310.redwoodforests.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RedwoodForests.MODID);

    public static final Supplier<CreativeModeTab> REDWOOD_FORESTS_TAB = CREATIVE_MODE_TABS.register("redwood_forests_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.REDWOOD_BARK.get()))
                    .title(Component.translatable("creativetab.redwoodforests.redwood_forests"))
                    .withTabsBefore(CreativeModeTabs.REDSTONE_BLOCKS)
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.REDWOOD_BARK);
                        output.accept(ModItems.CHARRED_REDWOOD_BARK);
                    })
                    .build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
