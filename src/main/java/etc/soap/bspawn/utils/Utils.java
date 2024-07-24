package etc.soap.bspawn.utils;

import etc.soap.bspawn.Main;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class Utils {

    public static void spawnLogic(Player player, boolean forceTp) {
        if (Main.CLEAR_INV) {
            player.getInventory().clear();
        }

        if (Main.HEAL_PLAYER) {
            player.setHealth(Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getDefaultValue());
            player.setFoodLevel(20);
        }

        if (forceTp || Main.TP_PLAYER) {
            player.teleport(Main.SPAWN_LOCATION);
        }
    }

    public static void onSpawnCommand(Player player) {
        YamlConfiguration config = Main.getConfiguration();
        List<String> commands = config.getStringList("on-spawn-command");
        if (commands == null || commands.isEmpty()) {
            return;
        }
        for (String command : commands) {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getConsoleSender(),
                    command.replace("%player%", player.getDisplayName())
            );
        }
    }
}
