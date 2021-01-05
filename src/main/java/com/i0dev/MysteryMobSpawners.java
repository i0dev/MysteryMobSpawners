package com.i0dev;

import com.i0dev.cmd.GiveMysteryMob;
import com.i0dev.engine.MysteryPlace;
import com.i0dev.util.CreateItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class MysteryMobSpawners extends JavaPlugin {
    private static MysteryMobSpawners i;

    public static MysteryMobSpawners get() {
        return i;
    }

    private HashMap<String, Double> spawnerChances = new HashMap();

    public MysteryMobSpawners() {
        MysteryMobSpawners.i = this;
    }

    public HashMap<String, Double> getSpawnerChances() {
        return this.spawnerChances;
    }

    public static boolean particlesEnabled;
    public static String givenSpawnerMessage;
    public static String hologramName;
    public static ItemStack MysteryMobItem;

    public static String Reload_Successful;
    public static String Reload_Usage;
    public static String Reload_No_Permission;
    public static String Mystery_Mob_Spawner_Received;

    public static String Mystery_Mob_Spawner_Give_Usage;
    public static String Mystery_Mob_Spawner_Give_Unknown_Member_Error;
    public static String Mystery_Mob_Spawner_Give_Amount_Error;
    public static String Mystery_Mob_Spawner_Give_Success;
    public static String Mystery_Mob_Spawner_Give_No_Permission;

    public static ItemStack getItemStack() {
        return MysteryMobItem;
    }

    public static String messagingConfigPrefix = "Messaging.";

    public void loadConfig() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        reloadConfig();
        particlesEnabled = getConfig().getBoolean("particlesEnabled");
        givenSpawnerMessage = getConfig().getString("givenSpawnerMessage");
        hologramName = getConfig().getString("hologramName");
        MysteryMobItem = CreateItemStack.createItem(this.getConfig().getString("mobSpawnerItem.material"), this.getConfig().getInt("mobSpawnerItem.data"), this.getConfig().getString("mobSpawnerItem.name"), this.getConfig().getStringList("mobSpawnerItem.lore"), this.getConfig().getBoolean("mobSpawnerItem.glowing"));
        Mystery_Mob_Spawner_Give_Usage = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Give_Usage");
        Mystery_Mob_Spawner_Give_Unknown_Member_Error = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Give_Unknown_Member_Error");
        Mystery_Mob_Spawner_Give_No_Permission = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Give_No_Permission");
        Mystery_Mob_Spawner_Give_Amount_Error = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Give_Amount_Error");
        Mystery_Mob_Spawner_Give_Success = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Give_Success");
        Mystery_Mob_Spawner_Received = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Received");
        Reload_Successful = getConfig().getString(messagingConfigPrefix + "Reload_Successful");
        Reload_Usage = getConfig().getString(messagingConfigPrefix + "Reload_Usage");
        Reload_No_Permission = getConfig().getString(messagingConfigPrefix + "Reload_No_Permission");

        saveConfig();
    }


    @Override
    public void onEnable() {
        System.out.println("Enabled Mystery Mob Spawners");
        getServer().getPluginManager().registerEvents(new MysteryPlace(this), this);
        getCommand("mms").setExecutor(new GiveMysteryMob(this));
        getConfig().options().copyDefaults(true);
        saveConfig();
        loadConfig();
        loadSpawnerChances();


    }

    @Override
    public void onDisable() {
        System.out.println("Disabled Mystery Mob Spawners");
    }

    public void loadSpawnerChances() {
        ConfigurationSection mobsSection = this.getConfig().getConfigurationSection("spawners");
        Iterator var2 = mobsSection.getKeys(false).iterator();

        while (var2.hasNext()) {
            String mob = (String) var2.next();
            this.spawnerChances.put(mob, mobsSection.getDouble(mob + ".chance"));
        }

    }
}