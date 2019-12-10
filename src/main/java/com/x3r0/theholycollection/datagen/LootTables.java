package com.x3r0.theholycollection.datagen;

import com.x3r0.theholycollection.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.FIRSTBLOCK, createStandardTableInvEnergy("firstblock", ModBlocks.FIRSTBLOCK));
    }
}