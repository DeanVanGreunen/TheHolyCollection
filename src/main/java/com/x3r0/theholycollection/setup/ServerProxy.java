package com.x3r0.theholycollection.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {
    @Override
    public void init() {

    }

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only Runs on Client");
    }

    @Override
    public PlayerEntity getClientPayer() {
        throw new IllegalStateException("Only Runs on Client");
    }
}
