package com.i0dev.cmd;

import com.i0dev.MysteryMobSpawners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveMysteryMob implements CommandExecutor {
    private MysteryMobSpawners i;

    public GiveMysteryMob(MysteryMobSpawners instance) {
        this.i = instance;
    }


    private String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        boolean isConsole = sender instanceof ConsoleCommandSender;

        try {
            sender = (Player) sender;
        } catch (Exception error) {
            sender = Bukkit.getServer().getConsoleSender();
        }

        if (cmd.getName().equalsIgnoreCase("mms")) {
            if (args.length == 0) {
                sender.sendMessage(color(i.Reload_Usage));
                sender.sendMessage(color(i.Mystery_Mob_Spawner_Give_Usage));
                return true;

            }
            if (args.length == 1 && args[0].equalsIgnoreCase("give")) {
                sender.sendMessage(color(i.Mystery_Mob_Spawner_Give_Usage));
                return true;

            }
            if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
                if (sender.hasPermission("mms.give") || (sender.isOp()) || isConsole || sender.getName().equalsIgnoreCase("console")) {
                    try {
                        Player player2give = Bukkit.getServer().getPlayer(args[1]);
                        int GiveAmount = 1;
                        player2give.getInventory().addItem(MysteryMobSpawners.getItemStack());
                        String new_Chunk_Buster_Received = i.Mystery_Mob_Spawner_Received;
                        String new_Mystery_Mob_Spawner_Give_Success = i.Mystery_Mob_Spawner_Give_Success;
                        if (new_Mystery_Mob_Spawner_Give_Success.contains("{amount}")) {
                            new_Mystery_Mob_Spawner_Give_Success = new_Mystery_Mob_Spawner_Give_Success.replace("{amount}", GiveAmount + "");
                        }
                        if (new_Mystery_Mob_Spawner_Give_Success.contains("{player}")) {
                            new_Mystery_Mob_Spawner_Give_Success = new_Mystery_Mob_Spawner_Give_Success.replace("{player}", player2give.getName() + "");
                        }

                        if (new_Chunk_Buster_Received.contains("{amount}")) {
                            new_Chunk_Buster_Received = new_Chunk_Buster_Received.replace("{amount}", GiveAmount + "");
                        }
                        if (new_Chunk_Buster_Received.contains("{player}")) {
                            new_Chunk_Buster_Received = new_Chunk_Buster_Received.replace("{player}", sender.getName() + "");
                        }
                        player2give.sendMessage(color(new_Chunk_Buster_Received));
                        sender.sendMessage(color(new_Mystery_Mob_Spawner_Give_Success));

                    } catch (Exception error) {
                        sender.sendMessage(color(i.Mystery_Mob_Spawner_Give_Unknown_Member_Error));
                        return true;

                    }
                    return true;
                } else {
                    sender.sendMessage(color(i.Mystery_Mob_Spawner_Give_No_Permission));
                    return true;
                }
            }
            if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
                if (sender.hasPermission("mms.give") || (sender.isOp()) || isConsole || sender.getName().equalsIgnoreCase("console")) {
                    String GiveAmount = "1";
                    try {
                        GiveAmount = args[2];
                        int UnUsed_ToDetectIfPlayerExists = Integer.parseInt(GiveAmount);
                    } catch (Exception error) {
                        sender.sendMessage(color(i.Mystery_Mob_Spawner_Give_Amount_Error));
                        return true;
                    }
                    try {
                        Player player2give = Bukkit.getServer().getPlayer(args[1]);
                        ItemStack itemToGive = MysteryMobSpawners.get().getItemStack();
                        itemToGive.setAmount(Integer.parseInt(GiveAmount));
                        player2give.getInventory().addItem(itemToGive);
                        String new_Chunk_Buster_Received = i.Mystery_Mob_Spawner_Received;
                        String new_Mystery_Mob_Spawner_Give_Success = i.Mystery_Mob_Spawner_Give_Success;
                        if (new_Mystery_Mob_Spawner_Give_Success.contains("{amount}")) {
                            new_Mystery_Mob_Spawner_Give_Success = new_Mystery_Mob_Spawner_Give_Success.replace("{amount}", GiveAmount + "");
                        }
                        if (new_Mystery_Mob_Spawner_Give_Success.contains("{player}")) {
                            new_Mystery_Mob_Spawner_Give_Success = new_Mystery_Mob_Spawner_Give_Success.replace("{player}", player2give.getName() + "");
                        }

                        if (new_Chunk_Buster_Received.contains("{amount}")) {
                            new_Chunk_Buster_Received = new_Chunk_Buster_Received.replace("{amount}", GiveAmount + "");
                        }
                        if (new_Chunk_Buster_Received.contains("{player}")) {
                            new_Chunk_Buster_Received = new_Chunk_Buster_Received.replace("{player}", sender.getName() + "");
                        }


                        player2give.sendMessage(color(new_Chunk_Buster_Received));
                        sender.sendMessage(color(new_Mystery_Mob_Spawner_Give_Success));

                    } catch (Exception error) {
                        sender.sendMessage(color(i.Mystery_Mob_Spawner_Give_Unknown_Member_Error));
                        return true;

                    }
                    return true;
                } else {
                    sender.sendMessage(color(i.Mystery_Mob_Spawner_Give_No_Permission));
                    return true;
                }
            }

            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("mms.reload") || sender.isOp() || isConsole || sender.getName().equalsIgnoreCase("console")) {
                    sender.sendMessage(color(i.Reload_Successful));
                    i.loadConfig();
                    return true;

                } else {
                    sender.sendMessage(color(i.Reload_No_Permission));
                    return true;
                }

            }
            return true;
        }


        return false;
    }
}
