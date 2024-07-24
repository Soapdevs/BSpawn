package etc.soap.bspawn.commands;

import etc.soap.bspawn.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a Player!");
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Unknown command. Please use: /setspawn");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("spawn.admin.setspawn")) {
            sender.sendMessage(ChatColor.RED + "You don't have the permissions to do that!");
            return true;
        }

        YamlConfiguration config = Main.getConfiguration();
        config.set("spawn-location", player.getLocation());
        Main.getConfObj().save();
        Main.reload();

        sender.sendMessage(ChatColor.GREEN + "Spawn set!");
        return true;
    }
}
