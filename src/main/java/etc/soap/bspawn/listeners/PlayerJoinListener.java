package etc.soap.bspawn.listeners;

import etc.soap.bspawn.Main;
import etc.soap.bspawn.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Main.SPAWN_LOCATION == null) {
            if (player.hasPermission("spawn.admin.setspawn")) {
                player.sendMessage(ChatColor.RED + "Please set the server spawn with /setspawn!");
            } else {
                player.sendMessage(ChatColor.RED + "There is a configuration error, please notify the server owner.");
            }
            return;
        }
        Utils.spawnLogic(player, false);
    }
}
