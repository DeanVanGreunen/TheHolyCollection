package com.x3r0.theholycollection.blockitems;

import com.x3r0.theholycollection.TheHolyCollection;
import com.x3r0.theholycollection.blocks.ModBlocks;
import net.minecraft.item.BlockItem;

public class FancyBlockItem extends BlockItem {
    public FancyBlockItem() {
        super(
                ModBlocks.FANCYBLOCK,
                new Properties().group(TheHolyCollection.setup.itemGroup)
        );
        setRegistryName("fancyblock");
    }
}
