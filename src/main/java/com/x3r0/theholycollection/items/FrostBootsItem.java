package com.x3r0.theholycollection.items;

import com.google.common.collect.Multimap;
import com.x3r0.theholycollection.TheHolyCollection;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.*;
import net.minecraftforge.common.*;


import javax.annotation.Nullable;

public class FrostBootsItem extends ArmorItem {
    private BlockState TransformBlockTo = Blocks.FROSTED_ICE.getDefaultState();
    public FrostBootsItem() {
        super(
                ArmorMaterial.DIAMOND,
                EquipmentSlotType.FEET,
                new Item.Properties().group(TheHolyCollection.setup.itemGroup)
        );
        setRegistryName("frostbootsitem");
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        SetIceBelowPlayer(player.getEntityWorld().getWorld(), player.getPosition());
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return "theholycollection:textures/armor/frostbootsitem.png";
    }

    private void SetIceBelowPlayer(World world, BlockPos p) {

        int px = p.getX();
        int py = p.getY();
        int pz = p.getZ();

        BlockPos p_1 = p.add( - 1, - 1, 1);
        BlockPos p_2 = p.add( + 0, - 1, 1);
        BlockPos p_3 = p.add(+ 1, - 1, 1);
        BlockPos p_4 = p.add(- 1, - 1, 0);
        BlockPos p_5 = p.add( 0, - 1, 0);
        BlockPos p_6 = p.add( 1, - 1, 0);
        BlockPos p_7 = p.add(- 1, - 1, - 1);
        BlockPos p_8 = p.add(0, - 1, 1);
        BlockPos p_9 = p.add( 1, - 1, - 1);
        MakeWaterIce(p_1, world);
        MakeWaterIce(p_2, world);
        MakeWaterIce(p_3, world);
        MakeWaterIce(p_4, world);
        MakeWaterIce(p_5, world);
        MakeWaterIce(p_6, world);
        MakeWaterIce(p_7, world);
        MakeWaterIce(p_8, world);
        MakeWaterIce(p_9, world);
    }

    private void MakeWaterIce(BlockPos pos, World world) {
        Chunk chunk = world.getChunkAt(pos);
        IFluidState f_state = chunk.getFluidState(pos);
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() == Blocks.WATER || (f_state.isTagged(FluidTags.WATER) || f_state.isTagged(FluidTags.LAVA))){
            world.setBlockState(pos, TransformBlockTo, 0);
        }
        world.markChunkDirty(pos, null);
    }
}
