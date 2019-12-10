package com.x3r0.theholycollection.setup;

import com.x3r0.theholycollection.blocks.ModBlocks;
import com.x3r0.theholycollection.blocks.firstblock.FirstBlockScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {
    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.FIRSTBLOCK_CONTAINER, FirstBlockScreen::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPayer() {
        return Minecraft.getInstance().player;
    }
}
