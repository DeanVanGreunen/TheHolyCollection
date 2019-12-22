package com.x3r0.theholycollection.items;

import com.x3r0.theholycollection.TheHolyCollection;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class HandsOfJesusItem extends Item {
    public HandsOfJesusItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(TheHolyCollection.setup.itemGroup));
        setRegistryName("handsofjesusitem");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity pIn, Hand handIn) {
        List<PlayerEntity> players = (List<PlayerEntity>) worldIn.getPlayers();
        for (PlayerEntity p: players) {
            float dist = Math.abs(getDistance(p.getPosition(),pIn.getPosition()));
            if(dist <= 10){
                p.heal(99999 );
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, new ItemStack(this));
    }

    public float getDistance(BlockPos a, BlockPos b){
        return (float)Math.sqrt(((b.getX() - a.getX())^2) + ((b.getY()-a.getY())^2) + ((b.getZ()-a.getZ())^2));
    }
}
