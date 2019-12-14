package com.x3r0.theholycollection.items;

import com.x3r0.theholycollection.TheHolyCollection;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class WaterSplitterItem extends Item {

    protected BlockState TransformBlockTo = Blocks.AIR.getDefaultState();

    public WaterSplitterItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(TheHolyCollection.setup.itemGroup));
        setRegistryName("watersplitteritem");
    }


    //TODO: Complete with directions, etc...
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        World world = worldIn;
        BlockPos blockpos = playerIn.getPosition();
        Direction facing = playerIn.getHorizontalFacing();
        switch (facing){
            case NORTH:
                RemoveWaterNorth(world, blockpos);
                break;
            case SOUTH:
                RemoveWaterSouth(world, blockpos);
                break;
            case EAST:
                RemoveWaterEast(world, blockpos);
                break;
            case WEST:
                RemoveWaterWest(world, blockpos);
                break;
        }
        return new ActionResult(ActionResultType.PASS, playerIn.getActiveItemStack());
    }

    public void RemoveWaterEast(World world, BlockPos pos) {
        for (int z = -8; z < 8; z++) {
            for (int y = -8; y < 8; y++) {
                for (int x = 0; x < 32; x++) {
                    RemoveWater(x, y, z, pos, world);
                }
            }
        }
    }

    public void RemoveWaterWest(World world, BlockPos pos) {
        for (int z = -8; z < 8; z++) {
            for (int y = -8; y < 8; y++) {
                for (int x = -32; x < 0; x++) {
                    RemoveWater(x, y, z, pos, world);
                }
            }
        }
    }

    public void RemoveWaterNorth(World world, BlockPos pos){
        for(int z = -32; z < 0;z++){
            for(int x = -8;x < 8; x++){
                for(int y = -8; y < 8; y++){
                    RemoveWater( x,y, z, pos, world);
                }
            }
        }
    }

    public void RemoveWaterSouth(World world, BlockPos pos){
        for(int z = 0; z < 32 ;z++){
            for(int x = -8;x < 8; x++){
                for(int y = -8; y < 8; y++){
                    RemoveWater( x,y, z, pos, world);
                }
            }
        }
    }

    public void RemoveWater(int x, int y, int z, BlockPos blockpos, World world){
        BlockPos pos = new BlockPos(blockpos.getX() + x, blockpos.getY() + y, blockpos.getZ() + z);
        BlockState state = world.getBlockState(pos);
        if(state.getBlock() == Blocks.WATER || hasWater(world, pos)) {
            world.setBlockState(pos, TransformBlockTo, 3);
        }
        world.markChunkDirty(pos, null);
    }

    public boolean hasWater(World world, BlockPos pos){
        Chunk chunk = world.getChunkAt(pos);
        IFluidState state = chunk.getFluidState(pos);
        return state.isTagged(FluidTags.WATER ) || state.isTagged(FluidTags.LAVA);
    }
}
