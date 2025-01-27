package me.kakaroot.kfilter2.Commands.SubCommands;

import me.kakaroot.kfilter2.Commands.Structure.SubCommand;
import me.kakaroot.kfilter2.KFilter2;
import me.kakaroot.kfilter2.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ListCommand implements SubCommand {

    private final KFilter2 plugin;

    public ListCommand(KFilter2 plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.tcc(plugin.PlayerPrefix + "&cIncorrect Format:"));
                sender.sendMessage(Utility.tcc(plugin.PlayerPrefix + "&aCorrect Format: &7/kf list"));
            } else {
                sender.sendMessage("Incorrect Format:");
                sender.sendMessage("Correct Format: kf list");
            }
            return false;
        }

        FileConfiguration config = plugin.getConfig();
        ConfigurationSection bannedWords = config.getConfigurationSection("banned_words");
        if (bannedWords == null) {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.tcc(plugin.PlayerPrefix + "&4Internal Error: &cFile configuration is null."));
            } else {
                sender.sendMessage("Internal Error: File Configuration is null.");
            }
            return false;
        }

        if (sender instanceof Player) {
            sender.sendMessage(Utility.tcc("\n&a&nBanned Words\n"));
        } else {
            sender.sendMessage("Banned Words:");
        }

        if (config.getKeys(false).size() > 0) {
            for (String banned : bannedWords.getKeys(false)) {
                String replacement = bannedWords.getString(banned);
                if (sender instanceof Player) {
                    sender.sendMessage(String.format(Utility.tcc("&c%s &f: &a%s"), banned, replacement));
                } else {
                    sender.sendMessage(String.format("%s : %s", banned, replacement));
                }
            }
            return true;
        } else {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.tcc("&c- &7NONE"));
            } else {
                sender.sendMessage("- NONE");
            }
        }
        return false;
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("kf.manage", "kf.list");
    }
}

