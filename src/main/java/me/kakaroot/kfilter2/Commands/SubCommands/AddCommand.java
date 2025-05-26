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

public class AddCommand implements SubCommand {

    private final KFilter2 plugin;

    public AddCommand(KFilter2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3) {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&cIncorrect Format:"));
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&7Correct Format: /kf add &c<word> &a<replacement>"));
            } else {
                sender.sendMessage("Incorrect Format:");
                sender.sendMessage("Correct Format: kf add <word> <replacement>");
            }
            return true;
        }

        String wordToReplace = args[1];
        StringBuilder replacementBuilder = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            replacementBuilder.append(args[i]).append(" ");
        }
        String replacement = replacementBuilder.toString().trim();

        FileConfiguration config = plugin.getConfig();
        ConfigurationSection bannedWords = config.getConfigurationSection("banned_words");
        if (bannedWords == null) {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&4Error: &cConfig file is null."));
            } else {
                sender.sendMessage("Error: Config file is null.");
            }
            return false;
        }
        if (bannedWords.contains(wordToReplace)) {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&cError: &cThat word is already banned!"));
            } else {
                sender.sendMessage("Error: That word is already banned!");
            }
            return false;
        }
        bannedWords.set(wordToReplace, replacement);

        plugin.saveConfig();
        plugin.reloadConfig();
        plugin.getListener().loadBannedWords();

        if (sender instanceof Player) {
            sender.sendMessage(String.format(Utility.colourise(plugin.PlayerPrefix + "&c%s &7has been banned with replacement: &a%s"), wordToReplace, replacement));
        } else {
            sender.sendMessage(String.format("%s has been banned with replacement: %s", wordToReplace, replacement));
        }
        return true;
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("kf.manage", "kf.add");
    }
}
