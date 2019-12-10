package com.x3r0.theholycollection.blockitems;

import com.x3r0.theholycollection.TheHolyCollection;
import com.x3r0.theholycollection.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class FirstBlockItem extends BlockItem {
    public FirstBlockItem() {
        super(
                ModBlocks.FIRSTBLOCK,
                new Item.Properties().group(TheHolyCollection.setup.itemGroup)
        );
        setRegistryName("firstblock");
    }
}
