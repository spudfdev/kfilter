package me.kakaroot.kfilter2.Commands.SubCommands;

import me.kakaroot.kfilter2.Commands.CommandStructure.SubCommand;
import me.kakaroot.kfilter2.KFilter2;
import me.kakaroot.kfilter2.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ReloadCommand implements SubCommand {

    private final KFilter2 plugin;
    public ReloadCommand(KFilter2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&cIncorrect Format:"));
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&7Correct Format: &a/kf reload"));
            }
            else {
                sender.sendMessage("Incorrect Format:");
                sender.sendMessage("Correct Format: kf reload");
            }
            return false;
        }

        plugin.reloadConfig();
        plugin.getListener().loadBannedWords();
        if (sender instanceof Player) {
            sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&aThe config has been reloaded."));
        }
        else {
            sender.sendMessage("The config has been reloaded.");
        }
        return true;
    }
    @Override
    public List<String> getPermissions() {
        return Arrays.asList("kf.reload","kf.manage");
    }
}