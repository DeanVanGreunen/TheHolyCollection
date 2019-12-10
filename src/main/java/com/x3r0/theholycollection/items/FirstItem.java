package com.x3r0.theholycollection.items;

import com.x3r0.theholycollection.TheHolyCollection;
import net.minecraft.item.Item;

public class FirstItem extends Item {
    public FirstItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(TheHolyCollection.setup.itemGroup));
        setRegistryName("firstitem");
    }
}
