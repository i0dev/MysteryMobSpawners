package com.i0dev.util;

import com.i0dev.MysteryMobSpawners;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GetRandomSpawnerString {
    private static MysteryMobSpawners instance;

    public GetRandomSpawnerString(MysteryMobSpawners instance) {
        GetRandomSpawnerString.instance = instance;
    }

    public static String getRandomSpawner() {
        HashMap<String, Double> spawners = instance.getSpawnerChances();
        RandomChance<String> randomChance = new RandomChance();
        Iterator var3 = spawners.entrySet().iterator();

        while (var3.hasNext()) {
            Map.Entry<String, Double> entry = (Map.Entry) var3.next();
            randomChance.add((Double) entry.getValue(), entry.getKey());
        }

        return (String) randomChance.next();
    }
}
