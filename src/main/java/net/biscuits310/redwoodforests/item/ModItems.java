package net.biscuits310.redwoodforests.item;

import net.biscuits310.redwoodforests.RedwoodForests;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RedwoodForests.MODID);

    public static final DeferredItem<Item> REDWOOD_BARK = ITEMS.registerSimpleItem("redwood_bark");
    public static final DeferredItem<Item> CHARRED_REDWOOD_BARK = ITEMS.registerSimpleItem("charred_redwood_bark");

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
