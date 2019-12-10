package net.minecraft.world.gen.feature.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.EmptyJigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.FeatureJigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.template.AlwaysTrueRuleTest;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.BlockStateMatchRuleTest;
import net.minecraft.world.gen.feature.template.RandomBlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleEntry;
import net.minecraft.world.gen.feature.template.RuleStructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;

public class SavannaVillagePools {
   public static void init() {
   }

   static {
      ImmutableList<StructureProcessor> immutablelist = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new RuleEntry(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), new RuleEntry(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), new RuleEntry(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.ACACIA_PLANKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.ACACIA_STAIRS, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.ACACIA_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.ACACIA_WOOD, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.ORANGE_TERRACOTTA, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.YELLOW_TERRACOTTA, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.RED_TERRACOTTA, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, Boolean.valueOf(true)).with(PaneBlock.SOUTH, Boolean.valueOf(true))), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, Boolean.valueOf(true)).with(PaneBlock.SOUTH, Boolean.valueOf(true))), new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.EAST, Boolean.valueOf(true)).with(PaneBlock.WEST, Boolean.valueOf(true))), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, Boolean.valueOf(true)).with(PaneBlock.WEST, Boolean.valueOf(true))), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MELON_STEM.getDefaultState()))));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/town_centers"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/savanna/town_centers/savanna_meeting_point_1"), 100), new Pair<>(new SingleJigsawPiece("village/savanna/town_centers/savanna_meeting_point_2"), 50), new Pair<>(new SingleJigsawPiece("village/savanna/town_centers/savanna_meeting_point_3"), 150), new Pair<>(new SingleJigsawPiece("village/savanna/town_centers/savanna_meeting_point_4"), 150), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/town_centers/savanna_meeting_point_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/town_centers/savanna_meeting_point_2", immutablelist), 1), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/town_centers/savanna_meeting_point_3", immutablelist), 3), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/town_centers/savanna_meeting_point_4", immutablelist), 3)), JigsawPattern.PlacementBehaviour.RIGID));
      ImmutableList<StructureProcessor> immutablelist1 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.ACACIA_PLANKS.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()), new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()), new RuleEntry(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()))));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/streets"), new ResourceLocation("village/savanna/terminators"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/savanna/streets/corner_01", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/streets/corner_03", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/streets/straight_02", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/streets/straight_04", immutablelist1), 7), new Pair<>(new SingleJigsawPiece("village/savanna/streets/straight_05", immutablelist1), 3), new Pair<>(new SingleJigsawPiece("village/savanna/streets/straight_06", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/streets/straight_08", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/streets/straight_09", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/streets/straight_10", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/streets/straight_11", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/streets/crossroad_02", immutablelist1), 1), new Pair<>(new SingleJigsawPiece("village/savanna/streets/crossroad_03", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/streets/crossroad_04", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/streets/crossroad_05", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/streets/crossroad_06", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/streets/crossroad_07", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/streets/split_01", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/streets/split_02", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/streets/turn_01", immutablelist1), 3)), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/zombie/streets"), new ResourceLocation("village/savanna/zombie/terminators"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/corner_01", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/corner_03", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/straight_02", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/straight_04", immutablelist1), 7), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/straight_05", immutablelist1), 3), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/straight_06", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/straight_08", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/straight_09", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/straight_10", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/straight_11", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/crossroad_02", immutablelist1), 1), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/crossroad_03", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/crossroad_04", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/crossroad_05", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/crossroad_06", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/crossroad_07", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/split_01", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/split_02", immutablelist1), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/streets/turn_01", immutablelist1), 3)), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
      ImmutableList<StructureProcessor> immutablelist2 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MELON_STEM.getDefaultState()))));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/houses"), new ResourceLocation("village/savanna/terminators"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_small_house_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_small_house_2"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_small_house_3"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_small_house_4"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_small_house_5"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_small_house_6"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_small_house_7"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_small_house_8"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_medium_house_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_medium_house_2"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_butchers_shop_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_butchers_shop_2"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_tool_smith_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_fletcher_house_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_shepherd_1"), 7), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_armorer_1"), 1), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_fisher_cottage_1"), 3), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_tannery_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_cartographer_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_library_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_mason_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_weaponsmith_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_weaponsmith_2"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_temple_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_temple_2"), 3), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_large_farm_1", immutablelist2), 4), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_large_farm_2", immutablelist2), 6), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_small_farm", immutablelist2), 4), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_animal_pen_1"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_animal_pen_2"), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_animal_pen_3"), 2), Pair.of(EmptyJigsawPiece.INSTANCE, 5)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/zombie/houses"), new ResourceLocation("village/savanna/zombie/terminators"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_small_house_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_small_house_2", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_small_house_3", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_small_house_4", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_small_house_5", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_small_house_6", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_small_house_7", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_small_house_8", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_medium_house_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_medium_house_2", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_butchers_shop_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_butchers_shop_2", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_tool_smith_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_fletcher_house_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_shepherd_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_armorer_1", immutablelist), 1), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_fisher_cottage_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_tannery_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_cartographer_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_library_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_mason_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_weaponsmith_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_weaponsmith_2", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_temple_1", immutablelist), 1), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_temple_2", immutablelist), 3), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_large_farm_1", immutablelist), 4), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_large_farm_2", immutablelist), 4), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_small_farm", immutablelist), 4), new Pair<>(new SingleJigsawPiece("village/savanna/houses/savanna_animal_pen_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_animal_pen_2", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/houses/savanna_animal_pen_3", immutablelist), 2), Pair.of(EmptyJigsawPiece.INSTANCE, 5)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/terminators"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/plains/terminators/terminator_01", immutablelist1), 1), new Pair<>(new SingleJigsawPiece("village/plains/terminators/terminator_02", immutablelist1), 1), new Pair<>(new SingleJigsawPiece("village/plains/terminators/terminator_03", immutablelist1), 1), new Pair<>(new SingleJigsawPiece("village/plains/terminators/terminator_04", immutablelist1), 1), new Pair<>(new SingleJigsawPiece("village/savanna/terminators/terminator_05", immutablelist1), 1)), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/zombie/terminators"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/plains/terminators/terminator_01", immutablelist1), 1), new Pair<>(new SingleJigsawPiece("village/plains/terminators/terminator_02", immutablelist1), 1), new Pair<>(new SingleJigsawPiece("village/plains/terminators/terminator_03", immutablelist1), 1), new Pair<>(new SingleJigsawPiece("village/plains/terminators/terminator_04", immutablelist1), 1), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/terminators/terminator_05", immutablelist1), 1)), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/trees"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.SAVANNA_TREE, IFeatureConfig.NO_FEATURE_CONFIG)), 1)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/decor"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/savanna/savanna_lamp_post_01"), 4), new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.SAVANNA_TREE, IFeatureConfig.NO_FEATURE_CONFIG)), 4), new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.HAY_PILE, IFeatureConfig.NO_FEATURE_CONFIG)), 4), new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.MELON_PILE, IFeatureConfig.NO_FEATURE_CONFIG)), 1), Pair.of(EmptyJigsawPiece.INSTANCE, 4)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/zombie/decor"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/savanna/savanna_lamp_post_01", immutablelist), 4), new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.SAVANNA_TREE, IFeatureConfig.NO_FEATURE_CONFIG)), 4), new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.HAY_PILE, IFeatureConfig.NO_FEATURE_CONFIG)), 4), new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.MELON_PILE, IFeatureConfig.NO_FEATURE_CONFIG)), 1), Pair.of(EmptyJigsawPiece.INSTANCE, 4)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/villagers"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/savanna/villagers/nitwit"), 1), new Pair<>(new SingleJigsawPiece("village/savanna/villagers/baby"), 1), new Pair<>(new SingleJigsawPiece("village/savanna/villagers/unemployed"), 10)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/savanna/zombie/villagers"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/savanna/zombie/villagers/nitwit"), 1), new Pair<>(new SingleJigsawPiece("village/savanna/zombie/villagers/unemployed"), 10)), JigsawPattern.PlacementBehaviour.RIGID));
   }
}