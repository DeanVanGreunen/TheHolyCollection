package com.x3r0.theholycollection.setup;

import com.x3r0.theholycollection.TheHolyCollection;
import com.x3r0.theholycollection.items.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheHolyCollection.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {
    @SubscribeEvent
    public static void onItemColor(ColorHandlerEvent.Item event){
        event.getItemColors().register((stack, i) -> 0xff0000, ModItems.WEIRDMOB_EGG);
    }
}
