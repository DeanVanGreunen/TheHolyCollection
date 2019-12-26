package com.x3r0.theholycollection.blocks.protectionblock;

import com.google.common.collect.Lists;
import com.x3r0.theholycollection.TheHolyCollection;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

import static com.x3r0.theholycollection.blocks.ModBlocks.PROTECTIONBLOCK_TILE;

public class ProtectionBlockTile extends TileEntity implements ITickableTileEntity {

    public ProtectionBlockTile() {
        super(PROTECTIONBLOCK_TILE);
    }

    /**
     * Returns a list of all entities within the given bounding box that match the class or interface provided
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getEntitiesWithinAABB(World world, Class<T> clazz, AxisAlignedBB aabb) {
        List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, aabb);
        List<T> found = Lists.newArrayList();
        Iterator<LivingEntity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity e = iterator.next();
            if (clazz.isAssignableFrom(e.getClass())) {
                found.add((T) e);
            }
        }
        return found;
    }

    @Override
    public void tick() {
        if (world.isRemote) {
            return;
        }
        BlockPos o_pos = getPos();
        BlockPos p0 = o_pos.add(-8, 0, -8);
        BlockPos p1 = o_pos.add(8, 16, 8);
        AxisAlignedBB aabb = new AxisAlignedBB(p0, p1);
        List<LivingEntity> mobs = getEntitiesWithinAABB(world, LivingEntity.class, aabb);
        float Radius = 35;
        for (LivingEntity mob : mobs) {
            if (!(mob instanceof PlayerEntity)) {
                BlockPos b = mob.getPosition();
                BlockPos a = getPos();
                float distance = Math.abs((float) (Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getZ() - a.getZ(), 2))));
                if (distance <= Radius) {
                    Vec2f pos = getNewPosition(new Vec2f(a.getX(), a.getZ()), new Vec2f(b.getX(), b.getZ()), Radius);
                    mob.setPositionAndUpdate(pos.x, b.getY(), pos.y);
                }
            }
        }
    }

    public Vec2f getNewPosition(Vec2f a, Vec2f b, float radius) {
        double angle = (float)AngleBetween(b, a);
        double x = radius * Math.cos(angle);
        double y = radius * Math.sin(angle);
        return new Vec2f((float)(x + a.x), (float)(y + a.y));
    }

    public double AngleBetween(Vec2f vector1, Vec2f vector2)
    {
        double sin = vector1.x * vector2.x - vector2.x * vector1.y;
        double cos = vector1.x * vector2.x + vector1.y * vector2.y;

        return Math.atan2(sin, cos) * (180 / Math.PI);
    }
}
