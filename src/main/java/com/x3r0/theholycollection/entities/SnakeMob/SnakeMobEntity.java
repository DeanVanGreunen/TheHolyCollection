package com.x3r0.theholycollection.entities.SnakeMob;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SnakeMobEntity extends AnimalEntity {

    public SnakeMobEntity(EntityType<? extends AnimalEntity> type, World worldIn){
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }
}
