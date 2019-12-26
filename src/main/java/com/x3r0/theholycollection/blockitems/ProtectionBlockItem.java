package com.x3r0.theholycollection.blockitems;

import com.x3r0.theholycollection.TheHolyCollection;
import com.x3r0.theholycollection.blocks.ModBlocks;
import net.minecraft.item.BlockItem;

public class ProtectionBlockItem extends BlockItem {
    public  ProtectionBlockItem() {
        super(
                ModBlocks.PROTECTIONBLOCK,
                new Properties().group(TheHolyCollection.setup.itemGroup)
        );
        setRegistryName("protectionblock");
    }
}
