package etc.soap.bspawn.listeners;

import etc.soap.bspawn.Main;
import etc.soap.bspawn.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(Main.SPAWN_LOCATION);
        new BukkitRunnable() {
            @Override
            public void run() {
                // Schedule the task to run on the main thread to ensure thread safety with Bukkit API calls
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Utils.spawnLogic(event.getPlayer(), false);
                    }
                }.runTask(Main.getInstance());
            }
        }.runTaskLaterAsynchronously(Main.getInstance(), 1);
    }
}
