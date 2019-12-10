package com.x3r0.theholycollection;

import com.x3r0.theholycollection.blockitems.FirstBlockItem;
import com.x3r0.theholycollection.blocks.firstblock.FirstBlock;
import com.x3r0.theholycollection.blocks.firstblock.FirstBlockContainer;
import com.x3r0.theholycollection.blocks.firstblock.FirstBlockTile;
import com.x3r0.theholycollection.blocks.ModBlocks;
import com.x3r0.theholycollection.items.FirstItem;
import com.x3r0.theholycollection.setup.ClientProxy;
import com.x3r0.theholycollection.setup.IProxy;
import com.x3r0.theholycollection.setup.ModSetup;
import com.x3r0.theholycollection.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("theholycollection")
public class TheHolyCollection {

    public static IProxy proxy = DistExecutor.runForDist(() -> ()-> new ClientProxy(), () -> () -> new ServerProxy());

    public static ModSetup setup = new ModSetup();

    public static final String MODID = "theholycollection";

    private static final Logger LOGGER = LogManager.getLogger();
    //LOGGER.info("HELLO FROM PREINIT");

    public TheHolyCollection() {
        // Register Here Anyway
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("theholycollection-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("theholycollection-common.toml"));
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Runs after all blocks/tiles/items/biohomes/etc
        setup.init();
        proxy.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        /*InterModComms.sendTo("theholycollection", "helloworld", () -> {
            return "Hello world";
        });*/
    }

    private void processIMC(final InterModProcessEvent event) {
        /*LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));*/
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        //LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            LOGGER.info("Registering Blocks");

            event.getRegistry().register(new FirstBlock());
        }
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            LOGGER.info("Registering Items");
            LOGGER.info("Registering BlockItems");

            event.getRegistry().register(new FirstBlockItem());
            event.getRegistry().register(new FirstItem());
        }
        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            LOGGER.info("Registering TileEntities");

            event.getRegistry().register(TileEntityType.Builder.create(FirstBlockTile::new, ModBlocks.FIRSTBLOCK).build(null).setRegistryName("firstblock"));
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
            LOGGER.info("Registering Containers");
            event.getRegistry().register(IForgeContainerType.create(
                    (windowId, inv, data)->{
                        BlockPos pos = data.readBlockPos();
                        return new FirstBlockContainer(windowId,TheHolyCollection.proxy.getClientWorld(), pos, inv, TheHolyCollection.proxy.getClientPayer());
                    }).setRegistryName("firstblock"));
        }
    }
}
