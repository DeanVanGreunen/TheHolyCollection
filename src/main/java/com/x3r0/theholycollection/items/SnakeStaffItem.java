package com.x3r0.theholycollection.items;

import com.x3r0.theholycollection.TheHolyCollection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.fixes.TileEntityId;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class SnakeStaffItem extends Item {
    public SnakeStaffItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(TheHolyCollection.setup.itemGroup));
        setRegistryName("snakestaffitem");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity pIn, Hand handIn) {
        //Summon Snake In Front Of Player...Might require another mod, or I'll have to implement a Snake Mob
        //////////////////////////////////////////////////////////////////////////////////////////////
        //EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("snake"));
        //entityType.spawn(worldIn, null, null, pIn.getPosition(), SpawnReason.SPAWN_EGG, true, true);

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, new ItemStack(this));
    }
}