package com.i0dev.util;

import com.i0dev.MysteryMobSpawners;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.concurrent.ThreadLocalRandom;

public class ArmorStandUtil {
    private static boolean doSounds = MysteryMobSpawners.soundsEnabled;
    private static boolean doParticles = MysteryMobSpawners.particlesEnabled;

    public static void setSmallRunnable(ArmorStand item, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                item.setSmall(false);

            }
        }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), delay);
    }

    public static void setNewItem(ArmorStand item, ItemStack newItem, long delay, Effect effect, Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (doParticles) {

                    item.getWorld().playEffect(item.getLocation().add(0, 1.24, 0), effect, 4000);
                }
                if (doSounds) {
                    playSound(player, Sound.PORTAL, 10L);
                }
                item.setHelmet(newItem);

                crack(item, 10L, 15, player);
                if (doSounds) {

                    playSound(player, Sound.CREEPER_HISS, 10L);
                }
                normal(item, 12L);
                crack(item, 13L, -15, player);
                if (doSounds) {

                    playSound(player, Sound.CREEPER_HISS, 13L);
                }
                normal(item, 17L);
                crack(item, 20L, 15, player);
                if (doSounds) {

                    playSound(player, Sound.CREEPER_HISS, 20L);
                }
                normal(item, 23L);
            }
        }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), delay);

    }

    public static void crack(ArmorStand item, long delay, double angleD, Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                EulerAngle angle = new EulerAngle((Math.toRadians(angleD)), 0, 0);
                if (doSounds) {

                    playSound(player, Sound.ITEM_BREAK, 1L);
                }
                item.setHeadPose(angle);

            }
        }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), delay);

    }

    public static void normal(ArmorStand item, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                EulerAngle angle = new EulerAngle((Math.toRadians(0)), 0, 0);
                item.setHeadPose(angle);

            }
        }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), delay);

    }

    public static void particleRunner(ArmorStand item, Player player, int randomize, long delay, com.i0dev.util.ParticleEffect Type) {
        if (doParticles) {

            new BukkitRunnable() {
                @Override
                public void run() {
                    double size = 0.1;
                    for (int i = 0; i < 360; i = i + randomize) {

                        double angle = (i * Math.PI / 180);
                        double x = size * Math.cos(angle);
                        double z = size * Math.sin(angle);
                        Location loc = item.getLocation().clone().add(0 + x, 1.5, 0 + z);

                        if (!player.getWorld().getName().equalsIgnoreCase(loc.getWorld().getName())) {
                            break;
                        }
                        Type.display(0, 0, 0, 2, 1, loc, player);
                    }

                }
            }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), delay);
        }
    }

    public static void particleRunnerAdvanced(ArmorStand item, Player player, int randomize, long delay, com.i0dev.util.ParticleEffect Type) {
        if (doParticles) {

            new BukkitRunnable() {
                @Override
                public void run() {
                    double size = 0.5;
                    for (int i = 0; i < 360; i = i + randomize) {

                        double angle = (i * Math.PI / 180);
                        double x = size * Math.cos(angle);
                        double z = size * Math.sin(angle);
                        Location loc = item.getLocation().clone().add(0.1 + x, 1.4, 0.1 + z);

                        if (!player.getWorld().getName().equalsIgnoreCase(loc.getWorld().getName())) {
                            break;
                        }
                        Type.display(((float) (ThreadLocalRandom.current().nextFloat() * (.5))), 0, ((float) (ThreadLocalRandom.current().nextFloat() * (.5))), 3, 2, loc, player);
                    }

                }
            }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), delay);
        }
    }

    public static void playSound(Player player, Sound sound, long delay) {
        if (doSounds) {

            new BukkitRunnable() {
                @Override
                public void run() {

                    player.playSound(player.getLocation(), sound, 1, 1);

                }
            }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), delay);
        }
    }

    public static void playEffect(Location actionLocation, ParticleEffect effect, long delay, Player player) {
        if (doParticles) {

            new BukkitRunnable() {
                @Override
                public void run() {
                    effect.display(0, 0, 0, 1, 1, actionLocation, player);
                }
            }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), delay);
        }
    }

    public static void playEffectMultiple(Location actionLocation, ParticleEffect effect, long delay, Player player, int Times) {
        if (doParticles) {

            new BukkitRunnable() {
                @Override
                public void run() {
                    for (int i = 0; i < Times; i++) {

                        Location loc = actionLocation.clone().add(0, 1.5 + i, 0);

                        if (!player.getWorld().getName().equalsIgnoreCase(loc.getWorld().getName())) {
                            break;
                        }

                        effect.display(0, 0, 0, 1, 1, loc, player);
                    }
                }

            }.runTaskLaterAsynchronously(MysteryMobSpawners.get(), delay);
        }
    }
}
