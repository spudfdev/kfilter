package me.kakaroot.kfilter2.Commands.SubCommands;

import me.kakaroot.kfilter2.Commands.CommandStructure.SubCommand;
import me.kakaroot.kfilter2.KFilter2;
import me.kakaroot.kfilter2.Utility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ClearChatCommand implements SubCommand {
    private final KFilter2 plugin;
    public ClearChatCommand(KFilter2 plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&cIncorrect Format:"));
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&aCorrect Format: &7/kf clearchat"));
            }
            else {
                sender.sendMessage("Incorrect Format:");
                sender.sendMessage("Correct Format: kf clearchat");
            }
            return false;
        }
        for (int i = 0; i < 150; i++) {
            Bukkit.broadcastMessage("");
        }
        Bukkit.broadcastMessage(Utility.colourise("&c|-------------------+====+-------------------|"));
        Bukkit.broadcastMessage(Utility.colourise("&a&lThe chat has been cleared."));
        Bukkit.broadcastMessage(Utility.colourise("&c|-------------------+====+-------------------|"));
        return true;
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("kf.manage","kf.clearchat");
    }
}
