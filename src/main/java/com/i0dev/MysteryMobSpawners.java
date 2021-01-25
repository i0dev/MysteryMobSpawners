package com.i0dev;

import com.i0dev.cmd.GiveMysteryMob;
import com.i0dev.engine.MysteryPlace;
import com.i0dev.util.ChunkLoading;
import com.i0dev.util.CreateItemStack;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class MysteryMobSpawners extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("Enabled Mystery Mob Spawners");
        getServer().getPluginManager().registerEvents(new MysteryPlace(this), this);
        chunkLoading = new ChunkLoading(this);
        getServer().getPluginManager().registerEvents(chunkLoading, this);
        getCommand("mms").setExecutor(new GiveMysteryMob(this));
        getConfig().options().copyDefaults(true);
        saveConfig();
        loadConfig();
        Bukkit.getScheduler().runTaskLaterAsynchronously(this, () -> loadSpawnerChances(getConfig().getConfigurationSection("spawners")), 200L) ;
    }

    @Override
    public void onDisable() {
        System.out.println("Disabled Mystery Mob Spawners");
    }


    public static boolean particlesEnabled;
    public static boolean soundsEnabled;
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
    public static String messagingConfigPrefix = "Messaging.";

    public void loadConfig() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        reloadConfig();
        particlesEnabled = getConfig().getBoolean("particlesEnabled");
        hologramName = getConfig().getString("hologramName");
        MysteryMobItem = CreateItemStack.createItem(this.getConfig().getString("mobSpawnerItem.material"), this.getConfig().getInt("mobSpawnerItem.data"), this.getConfig().getString("mobSpawnerItem.name"), this.getConfig().getStringList("mobSpawnerItem.lore"), this.getConfig().getBoolean("mobSpawnerItem.glowing"));
        Mystery_Mob_Spawner_Give_Usage = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Give_Usage");
        givenSpawnerMessage = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Spawner_Give");
        Mystery_Mob_Spawner_Give_Unknown_Member_Error = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Give_Unknown_Member_Error");
        Mystery_Mob_Spawner_Give_No_Permission = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Give_No_Permission");
        Mystery_Mob_Spawner_Give_Amount_Error = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Give_Amount_Error");
        Mystery_Mob_Spawner_Give_Success = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Give_Success");
        Mystery_Mob_Spawner_Received = getConfig().getString(messagingConfigPrefix + "Mystery_Mob_Spawner_Received");
        Reload_Successful = getConfig().getString(messagingConfigPrefix + "Reload_Successful");
        Reload_Usage = getConfig().getString(messagingConfigPrefix + "Reload_Usage");
        Reload_No_Permission = getConfig().getString(messagingConfigPrefix + "Reload_No_Permission");
        soundsEnabled = getConfig().getBoolean("soundsEnabled");
        saveConfig();
    }


    public void loadSpawnerChances(ConfigurationSection mobsSection) {

        for (String mob : mobsSection.getKeys(false)) {
            this.spawnerChances.put(mob, mobsSection.getDouble(mob + ".chance"));
        }

    }

    private static MysteryMobSpawners i;
    private ChunkLoading chunkLoading;

    public static MysteryMobSpawners get() {
        return i;
    }

    private final HashMap<String, Double> spawnerChances = new HashMap();

    public MysteryMobSpawners() {
        MysteryMobSpawners.i = this;
    }

    public ChunkLoading getChunkLoading() {
        return chunkLoading;
    }

    public HashMap<String, Double> getSpawnerChances() {
        return this.spawnerChances;
    }

    public static ItemStack getItemStack() {
        return MysteryMobItem;
    }
}