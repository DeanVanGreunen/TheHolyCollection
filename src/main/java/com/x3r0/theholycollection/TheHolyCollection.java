package com.x3r0.theholycollection;

import com.x3r0.theholycollection.blockitems.FancyBlockItem;
import com.x3r0.theholycollection.blockitems.FirstBlockItem;
import com.x3r0.theholycollection.blocks.fancyblock.FancyBlock;
import com.x3r0.theholycollection.blocks.firstblock.FirstBlock;
import com.x3r0.theholycollection.blocks.firstblock.FirstBlockContainer;
import com.x3r0.theholycollection.blocks.firstblock.FirstBlockTile;
import com.x3r0.theholycollection.blocks.ModBlocks;
import com.x3r0.theholycollection.dimension.TutorialModDimension;
import com.x3r0.theholycollection.entities.WeirdMobEntity;
import com.x3r0.theholycollection.items.FirstItem;
import com.x3r0.theholycollection.items.FrostBootsItem;
import com.x3r0.theholycollection.items.WaterSplitterItem;
import com.x3r0.theholycollection.items.WeirdMobEggItem;
import com.x3r0.theholycollection.setup.ClientProxy;
import com.x3r0.theholycollection.setup.IProxy;
import com.x3r0.theholycollection.setup.ModSetup;
import com.x3r0.theholycollection.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ModDimension;
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

import static com.x3r0.theholycollection.dimension.ModDimensions.DIMENSION_ID;

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
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            LOGGER.info("Registering Blocks");

            event.getRegistry().register(new FirstBlock());
            event.getRegistry().register(new FancyBlock());
        }
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            LOGGER.info("Registering Items");
            LOGGER.info("Registering BlockItems");

            event.getRegistry().register(new FancyBlockItem());
            event.getRegistry().register(new FirstBlockItem());
            event.getRegistry().register(new FirstItem());
            event.getRegistry().register(new WeirdMobEggItem());
            event.getRegistry().register(new WaterSplitterItem());
            event.getRegistry().register(new FrostBootsItem());
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


        @SubscribeEvent
        public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> event) {
            event.getRegistry().register(EntityType.Builder.create(WeirdMobEntity::new, EntityClassification.CREATURE)
            .size(1,1)
            .setShouldReceiveVelocityUpdates(false)
            .build("weirdmob").setRegistryName(TheHolyCollection.MODID, "weirdmob"));
        }

        @SubscribeEvent
        public static void registerModDimensions(final RegistryEvent.Register<ModDimension> event) {
            event.getRegistry().register(new TutorialModDimension().setRegistryName(DIMENSION_ID));
        }
    }
}
