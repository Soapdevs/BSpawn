package etc.soap.bspawn.commands;

import etc.soap.bspawn.Main;
import etc.soap.bspawn.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player!");
            return true;
        }

        if (Main.SPAWN_LOCATION == null) {
            if (sender.hasPermission("spawn.admin.setspawn")) {
                sender.sendMessage(ChatColor.RED + "Please set the server spawn with /setspawn!");
            } else {
                sender.sendMessage(ChatColor.RED + "There is a configuration error, please notify the server owner.");
            }
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            Utils.spawnLogic(player, true);
            Utils.onSpawnCommand(player);
            sender.sendMessage(ChatColor.GOLD + "Teleporting to spawn...");
        } else if (args.length == 1) {
            if (!sender.hasPermission("spawn.admin.others")) {
                sender.sendMessage(ChatColor.RED + "You don't have the permissions to do that!");
                return true;
            }
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                sender.sendMessage(ChatColor.RED + "The player " + args[0] + " was not found!");
                return true;
            }
            Utils.spawnLogic(targetPlayer, true);
            Utils.onSpawnCommand(targetPlayer);
            sender.sendMessage(ChatColor.GOLD + "Teleporting " + args[0] + " to spawn...");
        } else {
            sender.sendMessage(ChatColor.RED + "Unknown command. Please use: /spawn [<player>]");
        }

        return true;
    }
}
