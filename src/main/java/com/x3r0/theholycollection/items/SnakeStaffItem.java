package com.x3r0.theholycollection.items;

import com.x3r0.theholycollection.TheHolyCollection;
import com.x3r0.theholycollection.entities.ModEntities;
import com.x3r0.theholycollection.entities.SnakeMob.SnakeMobEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.datafix.fixes.TileEntityId;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

public class SnakeStaffItem extends Item {
    public SnakeStaffItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(TheHolyCollection.setup.itemGroup));
        setRegistryName("snakestaffitem"); //Holy Snake Staff of Moses
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
        //Summon Snake In Front Of Player...Might require another mod, or I'll have to implement a Snake Mob
        //////////////////////////////////////////////////////////////////////////////////////////////
        //EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("snake"));
        //entityType.spawn(worldIn, null, null, pIn.getPosition(), SpawnReason.SPAWN_EGG, true, true);
        BlockPos spawnpos = null;
        switch (player.getHorizontalFacing()) {
            case EAST:
                spawnpos = player.getPosition().add(2, 0, 0);
                break;
            case WEST:
                spawnpos = player.getPosition().add(-2, 0, 0);
                break;
            case NORTH:
                spawnpos = player.getPosition().add(0, 0, -2);
                break;
            case SOUTH:
                spawnpos = player.getPosition().add(0, 0, 2);
                break;
        }
        if (spawnpos != null) {
            SnakeMobEntity snakeMobEntity = ModEntities.SNAKEMOB.create(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnpos, SpawnReason.MOB_SUMMONED, false, false);
            world.addEntity(snakeMobEntity);
            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, new ItemStack(this));
        }
        System.out.println("FAILED");
        return new ActionResult<ItemStack>(ActionResultType.FAIL, new ItemStack(this));
    }
}