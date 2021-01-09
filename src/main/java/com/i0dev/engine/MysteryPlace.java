package com.i0dev.engine;

import com.i0dev.MysteryMobSpawners;
import com.i0dev.util.*;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MysteryPlace implements Listener {
    private MysteryMobSpawners instance;

    public MysteryPlace(MysteryMobSpawners instance) {
        this.instance = instance;
    }

    public String getRandomSpawner() {
        HashMap<String, Double> spawners = instance.getSpawnerChances();
        RandomChance<String> randomChance = new RandomChance();
        Iterator var3 = spawners.entrySet().iterator();

        while (var3.hasNext()) {
            Map.Entry<String, Double> entry = (Map.Entry) var3.next();
            randomChance.add((Double) entry.getValue(), entry.getKey());
        }

        return (String) randomChance.next();
    }

    @EventHandler(
            priority = EventPriority.HIGH,
            ignoreCancelled = true
    )
    public void onMysteryOpen(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getPlayer().getItemInHand().getType() == Material.AIR) return;
        if (e.getItem().getType() == Material.AIR) return;
        if (!e.getPlayer().getItemInHand().hasItemMeta()) return;
        if (!e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()) return;
        if (!e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(MysteryMobSpawners.getItemStack().getItemMeta().getDisplayName()))
            return;
        if (!e.getPlayer().getItemInHand().getItemMeta().getLore().equals(MysteryMobSpawners.getItemStack().getItemMeta().getLore()))
            return;
        e.setCancelled(true);
        Player player = e.getPlayer();
        ArmorStandUtil.playSound(player, Sound.FIREWORK_LAUNCH, 1L);

        PlayerUtils.removeOneItemInHandFromPlayer(player);
        Location actionLocation = e.getClickedBlock().getLocation().add(0, 1, 0);
        String RandomSpawner = getRandomSpawner();
        Iterator Command = this.instance.getConfig().getStringList("spawners." + RandomSpawner + ".command").iterator();
        String Type = this.instance.getConfig().getString("spawners." + RandomSpawner + ".displayName");
        ArmorStand item = (ArmorStand) actionLocation.getWorld().spawnEntity(actionLocation.add(0.5, 0.25, 0.5), EntityType.ARMOR_STAND);
        item.setHelmet(new ItemStack(Material.MOB_SPAWNER));
        item.setCustomName(ChatColor.translateAlternateColorCodes('&', MysteryMobSpawners.hologramName));
        item.setCustomNameVisible(true);
        item.setGravity(false);
        item.setCanPickupItems(false);
        item.setSmall(true);
        item.setVisible(false);
        item.setMarker(true);
        item.setHeadPose(new EulerAngle(0, 60, 0));
        ArmorStandUtil.spinHead(item, 0, 1L, 180);
        MysteryMobSpawners.get().getChunkLoading().addChunk(e.getClickedBlock().getChunk());
        ArmorStandUtil.playSound(player, Sound.CHICKEN_EGG_POP, 20L);
        ArmorStandUtil.setSmallRunnable(item, 20L, false);
        ArmorStandUtil.playSound(player, Sound.EXPLODE, 50L);
        ArmorStandUtil.setNewItem(item, new ItemStack(Material.DRAGON_EGG), 50L, Effect.EXPLOSION_HUGE, e.getPlayer());
        ArmorStandUtil.particleRunner(item, e.getPlayer(), 1, 20L, ParticleEffect.CRIT_MAGIC);
        ArmorStandUtil.particleRunnerAdvanced(item, e.getPlayer(), 1, 20L, ParticleEffect.SUSPENDED_DEPTH);
        ArmorStandUtil.particleRunner(item, e.getPlayer(), 1, 50L, ParticleEffect.PORTAL);

        ArmorStandUtil.playSound(player, Sound.LEVEL_UP, 120L);

        new BukkitRunnable() {
            @Override
            public void run() {
                actionLocation.getWorld().playEffect(actionLocation, Effect.EXPLOSION_LARGE, 4000);
                ArmorStandUtil.playEffect(actionLocation, ParticleEffect.FLAME, 1L, e.getPlayer());
                ArmorStandUtil.playEffectMultiple(actionLocation.add(0, -.75, 0), ParticleEffect.LAVA, 1L, e.getPlayer(), 5);
                ArmorStandUtil.playEffectMultiple(actionLocation.add(0, -.75, 0), ParticleEffect.LAVA, 3L, e.getPlayer(), 5);
                ArmorStandUtil.playEffectMultiple(actionLocation.add(0, -.75, 0), ParticleEffect.LAVA, 6L, e.getPlayer(), 5);
                ArmorStandUtil.playSound(player, Sound.EXPLODE, 1L);

                item.setCustomName(ChatColor.translateAlternateColorCodes('&', Type));
                actionLocation.getWorld().playEffect(actionLocation.add(0, 1.24, 0), Effect.VILLAGER_THUNDERCLOUD, 4000);
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', MysteryMobSpawners.givenSpawnerMessage.replace("%DISPLAYNAME%", Type)));

                item.setHelmet(TranslateSkulls.translateMobSkull(RandomSpawner));

            }
        }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), 100L);
        new BukkitRunnable() {
            @Override
            public void run() {
                String command = (String) Command.next();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
            }
        }.runTaskLater(MysteryMobSpawners.get(), 125L);
        new BukkitRunnable() {
            @Override
            public void run() {
                ArmorStandUtil.playEffectMultiple(actionLocation, ParticleEffect.CLOUD, 1L, e.getPlayer(), 10);
                ArmorStandUtil.playSound(player, Sound.FIZZ, 1L);
            }
        }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), 190L);
        new BukkitRunnable() {
            @Override
            public void run() {
                item.remove();
                MysteryMobSpawners.get().getChunkLoading().removeChunk(e.getClickedBlock().getChunk());
            }
        }.runTaskLater(MysteryMobSpawners.get(), 190L);
    }
}