package me.kakaroot.kfilter2.Commands.SubCommands;

import me.kakaroot.kfilter2.Commands.Structure.SubCommand;
import me.kakaroot.kfilter2.KFilter2;
import me.kakaroot.kfilter2.Utility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ClearchatCommand implements SubCommand {
    private final KFilter2 plugin;
    public ClearchatCommand(KFilter2 plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.tcc(plugin.PlayerPrefix + "&cIncorrect Format:"));
                sender.sendMessage(Utility.tcc(plugin.PlayerPrefix + "&aCorrect Format: &7/kf clearchat"));
            }
            else {
                sender.sendMessage("&cIncorrect Format:");
                sender.sendMessage("&aCorrect Format: &7/kf clearchat");
            }
            return false;
        }
        for (int i = 0; i < 150; i++) {
            Bukkit.broadcastMessage("");
        }
        Bukkit.broadcastMessage(Utility.tcc("|-------------------+====+-------------------|"));
        Bukkit.broadcastMessage(Utility.tcc("&fThe chat has been cleared."));
        Bukkit.broadcastMessage(Utility.tcc("|-------------------+====+-------------------|"));
        return true;
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("kf.manage","kf.clearchat");
    }
}
