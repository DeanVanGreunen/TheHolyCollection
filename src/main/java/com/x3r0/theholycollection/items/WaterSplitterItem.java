package com.x3r0.theholycollection.items;

import com.x3r0.theholycollection.TheHolyCollection;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.extensions.IForgeItem;
import net.minecraftforge.fluids.FluidUtil;

import static net.minecraft.util.Direction.*;

public class WaterSplitterItem extends Item {

    public WaterSplitterItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(TheHolyCollection.setup.itemGroup));
        setRegistryName("watersplitteritem");
    }


    //TODO: Complete with directions, etc...
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack1 = playerIn.getActiveItemStack();
        World world = worldIn;
        BlockPos blockpos = playerIn.getPosition();
        Direction facing = playerIn.getHorizontalFacing();
        int Z_NORTH_MIN = 0;
        int Z_NORTH_MAX = -32; //2 chunks

        int Z_SOUTH_MIN = 0;
        int Z_SOUTH_MAX = 32; //2 chunks

        int X_EAST_MIN = 0;
        int X_EAST_MAX = 32; //2 chunks

        int X_WEST_MIN = 0;
        int X_WEST_MAX = -32; //2 chunks

        int Y_MIN = -16;
        int Y_MAX = 16;
        int X_MIN = -16;
        int X_MAX = 16;
        int Z_MIN = -16;
        int Z_MAX = 16;

        switch (facing){
            case NORTH:
                for (int y = Y_MIN; y < Y_MAX; y++) {
                    for (int z = Z_SOUTH_MAX; z > Z_SOUTH_MIN; z--) {
                        for (int x = X_MIN; x < X_MAX; x++) {
                            RemoveWater( x, y, z, blockpos, world);
                        }
                    }
                }
                break;
            case SOUTH:
                for (int y = Y_MIN; y < Y_MAX; y++) {
                    for (int z = Z_SOUTH_MIN; z < Z_SOUTH_MAX; z++) {
                        for (int x = X_MIN; x < X_MAX; x++) {
                            RemoveWater( x, y, z, blockpos, world);
                        }
                    }
                }
                break;
            case EAST:
                for (int y = Y_MIN; y < Y_MAX; y++) {
                    for (int z = Z_MIN; z < Z_MAX; z++) {
                        for (int x = X_EAST_MIN; x < X_EAST_MAX; x++) {
                            RemoveWater( x, y, z, blockpos, world);
                        }
                    }
                }
                break;
            case WEST:
                for (int y = Y_MIN; y < Y_MAX; y++) {
                    for (int z = Z_MIN; z < Z_MAX; z++) {
                        for (int x = X_EAST_MAX; x > X_EAST_MIN; x--) {
                            RemoveWater( x, y, z, blockpos, world);
                        }
                    }
                }
                break;
        }
        //-z = north
        //+x = east
        //-x = west
        //z = south
        return new ActionResult(ActionResultType.SUCCESS, itemstack1);
    }

    public void RemoveWater(int x, int y, int z, BlockPos blockpos, World world){
        BlockPos pos = new BlockPos(blockpos.getX() + x, blockpos.getY() + y, blockpos.getZ() + z);
        BlockState state = world.getBlockState(pos);
        if(state.getBlock() == Blocks.WATER) {
            world.setBlockState(pos,Blocks.AIR.getDefaultState(), 3);
        }
        world.markChunkDirty(pos, null);
    }

    /*public boolean hasWater(World world, BlockPos pos){
        Chunk chunk = world.getChunkAt(pos);
        IFluidState state = chunk.getFluidState(pos);
        boolean rm = state.isTagged(FluidTags.WATER ) || state.isTagged(FluidTags.LAVA);
        return rm;
    }*/
}
