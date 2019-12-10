package com.x3r0.theholycollection.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {
    void init();
    World getClientWorld();
    PlayerEntity getClientPayer();
}
