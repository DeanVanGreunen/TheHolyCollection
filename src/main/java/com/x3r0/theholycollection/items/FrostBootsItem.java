package com.x3r0.theholycollection.items;

import com.google.common.collect.Multimap;
import com.x3r0.theholycollection.TheHolyCollection;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

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
        SetIceBelowPlayer(world, player.getPosition());
    }

    public void SetIceBelowPlayer(World world, BlockPos p){
        int px = p.getX();
        int py = p.getY();
        int pz = p.getZ();

        BlockPos p_1 = p.add(px-1,py+0,pz+1);
        BlockPos p_2 = p.add(px+0,py+0,pz+1);
        BlockPos p_3 = p.add(px+1,py+0,pz+1);
        BlockPos p_4 = p.add(px-1,py+0,pz+0);
        BlockPos p_5 = p.add(px+0,py+0,pz+0);
        BlockPos p_6 = p.add(px+1,py,pz+0);
        BlockPos p_7 = p.add(px-1,py,pz-1);
        BlockPos p_8 = p.add(px+0,py,pz-1);
        BlockPos p_9 = p.add(px+1,py,pz-1);
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

    public void MakeWaterIce(BlockPos pos, World world){
        BlockState state = world.getBlockState(pos);
        if(state.getBlock() == Blocks.WATER || hasWater(world, pos)) {
            world.setBlockState(pos, TransformBlockTo, 3);
        }
        world.markChunkDirty(pos, null);
    }

    public boolean hasWater(World world, BlockPos pos){
        Chunk chunk = world.getChunkAt(pos);
        IFluidState state = chunk.getFluidState(pos);
        return state.isTagged(FluidTags.WATER );
    }

    public boolean hasLava(World world, BlockPos pos){
        Chunk chunk = world.getChunkAt(pos);
        IFluidState state = chunk.getFluidState(pos);
        return state.isTagged(FluidTags.LAVA);
    }

}
