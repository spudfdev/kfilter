package me.kakaroot.kfilter2.Commands.SubCommands;

import me.kakaroot.kfilter2.Commands.CommandStructure.SubCommand;
import me.kakaroot.kfilter2.KFilter2;
import me.kakaroot.kfilter2.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class RemoveCommand implements SubCommand {

    private final KFilter2 plugin;
    public RemoveCommand(KFilter2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&cIncorrect Format:"));
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&aCorrect Format &7/kf remove <word>"));
            }
            else {
                sender.sendMessage("Incorrect Format:");
                sender.sendMessage("Correct Format: kf remove <word>");
            }
        }
        else {
            FileConfiguration config = plugin.getConfig();
            ConfigurationSection bannedWords = config.getConfigurationSection("banned_words");
            if (bannedWords == null) {
                if (sender instanceof Player) {
                    sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&4Error: &cConfig file is null."));
                }
                else {
                    sender.sendMessage("Error: Config file is null.");
                }
                return false;
            }

            if (!config.getKeys(false).isEmpty()) {
                String word = args[1];
                if (bannedWords.contains(word)) {
                    bannedWords.set(word,null);
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    plugin.getListener().loadBannedWords();
                    if (sender instanceof Player) {
                        sender.sendMessage(String.format(Utility.colourise(plugin.PlayerPrefix + "&a%s has been unbanned."),word));
                    }
                    else {
                        sender.sendMessage(String.format("%s has been unbanned.",word));
                    }
                    return true;
                }
                else {
                    if (sender instanceof Player) {
                        sender.sendMessage(Utility.colourise("&cError: That word isn't banned!"));
                    }
                    else {
                        sender.sendMessage("Error: That word isn't banned!");
                    }
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("kf.manage", "kf.remove");
    }
}
