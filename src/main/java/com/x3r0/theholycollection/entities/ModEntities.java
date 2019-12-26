package com.x3r0.theholycollection.entities;

import com.x3r0.theholycollection.entities.SnakeMob.SnakeMobEntity;
import com.x3r0.theholycollection.entities.WeirdMob.WeirdMobEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModEntities {
    @ObjectHolder("theholycollection:weirdmob")
    public static EntityType<WeirdMobEntity> WEIRDMOB;

    @ObjectHolder("theholycollection:snakemob")
    public static EntityType<SnakeMobEntity> SNAKEMOB;

}
