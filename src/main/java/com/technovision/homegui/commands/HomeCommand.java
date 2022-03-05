package com.technovision.homegui.commands;

import com.technovision.homegui.Homegui;
import com.technovision.homegui.gui.HomeGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    public static final String HOME = "home";
    public static final String H = "h";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Home GUI
            if (cmd.getName().equalsIgnoreCase(HOME)) {
                if (args.length == 0) {
                    HomeGUI gui = new HomeGUI(player.getUniqueId());
                    player.openInventory(gui.getInventory());
                } else if (args.length == 1) {
                    if (!(args[0].equalsIgnoreCase("reload"))){
                        if (player.hasPermission("home.tp.others")||player.hasPermission("home.*")||player.isOp()){
                            player.performCommand("essentials:home " + args[0]);
                        }else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&'
                                    ,"&c*-------------------------------------------*"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&'
                                    ,"&4Sorry you don't have the permission to do that!"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&'
                                    ,"&4Use &e/homes &4to open the GUI!"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&'
                                    ,"&c*-------------------------------------------*"));
                        }
                    }else {
                        if (player.hasPermission("home.rl")||player.hasPermission("home.*")||player.isOp()){
                            Homegui.PLUGIN.reloadConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&'
                                    ,"&7[&eHomeGUI&7]&a: Config file reloaded"));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&'
                                    ,"&4You do not have permission to use &e/h reload&4!"));
                        }
                    }
                }
            }

            // Reload
            else if (cmd.getName().equalsIgnoreCase(H)) {
                if (args.length == 0) {
                    player.performCommand("homegui:home");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        if (player.hasPermission("home.rl")||player.hasPermission("home.*")||player.isOp()) {
                            Homegui.PLUGIN.reloadConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&'
                                    ,"&7[&eHomeGUI&7]&a: Config file reloaded"));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&'
                                    ,"&4You do not have permission to use &e/h reload&4!"));
                        }
                    }
                }
            }

        }else {
            if (cmd.getName().equalsIgnoreCase(H)||cmd.getName().equalsIgnoreCase(HOME)){
                if (args.length == 1){
                    if (args[0].equalsIgnoreCase("reload")){
                        Homegui.PLUGIN.reloadConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&'
                                ,"&7[&eHomeGUI&7]&a: Config file reloaded"));
                    }
                }else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&'
                            ,"&cOnly players can use this command!"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&'
                            ,"&cTry using &e/h reload &cto reload the config"));
                }
            }
        }
        return true;
    }
}
