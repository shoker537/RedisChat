package dev.unnm3d.redischat.moderation;

import dev.unnm3d.redischat.RedisChat;
import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class SpyChatCommand implements CommandExecutor {
    private final RedisChat plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String playerName = sender.getName();
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                plugin.config.sendMessage(sender, plugin.config.player_not_online.replace("%player%", sender.getName()));
                return true;
            }
        } else {
            playerName = args[0];
        }

        if (plugin.getSpyManager().toggleSpying(playerName)) {
            plugin.config.sendMessage(sender, plugin.getComponentProvider().parse(plugin.config.spychat_enabled.replace("%player%", playerName)));
        } else {
            plugin.config.sendMessage(sender, plugin.getComponentProvider().parse(plugin.config.spychat_disabled.replace("%player%", playerName)));
        }
        return true;
    }
}
