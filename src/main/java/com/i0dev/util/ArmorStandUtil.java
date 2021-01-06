package com.i0dev.util;

import com.i0dev.MysteryMobSpawners;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class ArmorStandUtil {
    private static boolean doSounds = MysteryMobSpawners.soundsEnabled;
    private static boolean doParticles = MysteryMobSpawners.particlesEnabled;

    public static void setSmallRunnable(ArmorStand item, long delay, boolean bool) {
        new BukkitRunnable() {
            @Override
            public void run() {
                item.setSmall(bool);

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

                item.setHelmet(newItem);

                if (doSounds) {
                    playSound(player, Sound.PORTAL, 9L);
                    playEffectMultiple(item.getLocation(), ParticleEffect.CLOUD, 10L, player, 5);
                    playSound(player, Sound.ENDERDRAGON_HIT, 10L);
                    playEffectMultiple(item.getLocation(), ParticleEffect.CLOUD, 13L, player, 5);
                    playSound(player, Sound.ENDERDRAGON_HIT, 13L);
                    playEffectMultiple(item.getLocation(), ParticleEffect.CLOUD, 20L, player, 5);
                    playSound(player, Sound.ENDERDRAGON_HIT, 20L);
                    playEffectMultiple(item.getLocation(), ParticleEffect.CLOUD, 25L, player, 5);
                    playSound(player, Sound.ENDERDRAGON_HIT, 25L);
                    playEffectMultiple(item.getLocation(), ParticleEffect.CLOUD, 30L, player, 5);
                    playSound(player, Sound.ENDERDRAGON_HIT, 30L);
                }


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

    public static void spinHead(ArmorStand item, long delay, long interval, int runTime) {

        AtomicInteger counter = new AtomicInteger(0);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (counter.get() >= runTime) {
                    cancel();
                    return;
                }
                EulerAngle angle = new EulerAngle(0.0, Math.toRadians(item.getHeadPose().getY() + (1.0 * counter.get())), 0.0);
                item.setHeadPose(angle);

                counter.getAndIncrement();
            }

        }.runTaskTimerAsynchronously(MysteryMobSpawners.get(), delay, (interval / 2) / 2);

    }
}
