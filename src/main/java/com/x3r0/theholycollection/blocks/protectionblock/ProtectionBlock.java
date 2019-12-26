package com.x3r0.theholycollection.blocks.protectionblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class ProtectionBlock extends Block {
    public ProtectionBlock(){
        super(
                Properties.create(Material.IRON)
                        .sound(SoundType.METAL)
                        .hardnessAndResistance(2.0f)
        );
        setRegistryName("protectionblock");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ProtectionBlockTile();
    }


    public static Direction getFacingFromEntity(BlockPos clickedBox, LivingEntity entity) {
        return Direction.getFacingFromVector(
                (float) (entity.posX - clickedBox.getX()),
                (float) (entity.posY - clickedBox.getY()),
                (float) (entity.posZ - clickedBox.getZ())
        );
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
