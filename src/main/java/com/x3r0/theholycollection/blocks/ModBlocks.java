package com.x3r0.theholycollection.blocks;

import com.x3r0.theholycollection.blocks.fancyblock.FancyBlock;
import com.x3r0.theholycollection.blocks.fancyblock.FancyBlockTile;
import com.x3r0.theholycollection.blocks.firstblock.FirstBlock;
import com.x3r0.theholycollection.blocks.firstblock.FirstBlockContainer;
import com.x3r0.theholycollection.blocks.firstblock.FirstBlockTile;
import com.x3r0.theholycollection.blocks.protectionblock.ProtectionBlock;
import com.x3r0.theholycollection.blocks.protectionblock.ProtectionBlockTile;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {
    @ObjectHolder("theholycollection:firstblock")
    public static FirstBlock FIRSTBLOCK;

    @ObjectHolder("theholycollection:firstblock")
    public static TileEntityType<FirstBlockTile> FIRSTBLOCK_TILE;

    @ObjectHolder("theholycollection:firstblock")
    public static ContainerType<FirstBlockContainer> FIRSTBLOCK_CONTAINER;

    @ObjectHolder("theholycollection:fancyblock")
    public static FancyBlock FANCYBLOCK;

    @ObjectHolder("theholycollection:fancyblock")
    public static TileEntityType<FancyBlockTile> FANCYBLOCK_TILE;

    @ObjectHolder("theholycollection:protectionblock")
    public static ProtectionBlock PROTECTIONBLOCK;

    @ObjectHolder("theholycollection:protectionblock")
    public static TileEntityType<ProtectionBlockTile> PROTECTIONBLOCK_TILE;
}

