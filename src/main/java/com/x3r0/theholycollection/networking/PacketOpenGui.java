package com.x3r0.theholycollection.networking;

import com.x3r0.theholycollection.gui.SpawnerScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;


public class PacketOpenGui {

    public PacketOpenGui(PacketBuffer buf) {
    }

    public void toBytes(PacketBuffer buf) {
    }

    public PacketOpenGui() {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(SpawnerScreen::open);
        ctx.get().setPacketHandled(true);
    }

}

