package com.x3r0.theholycollection.setup;

import com.x3r0.theholycollection.blocks.ModBlocks;
import com.x3r0.theholycollection.networking.Networking;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ModSetup {

    public ItemGroup itemGroup = new ItemGroup("theholycollection") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.FIRSTBLOCK);
        }
    };

    public void init(){
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());
        Networking.registerMessages();
    }
}
