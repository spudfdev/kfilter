package me.kakaroot.kfilter2.Commands.CommandStructure;

import me.kakaroot.kfilter2.KFilter2;
import me.kakaroot.kfilter2.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseCommand implements CommandExecutor {

    private final KFilter2 plugin;
    private final Map<String, SubCommand> subCommands = new HashMap<>();

    public BaseCommand(KFilter2 plugin) {
        this.plugin = plugin;
    }

    public void registerSubCommand(String name, SubCommand subCommand) {
        subCommands.put(name.toLowerCase(), subCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.colourise("&6&lKFilter"));
                sender.sendMessage(Utility.colourise("&6Created by: &aSpud"));
                sender.sendMessage(Utility.colourise("&cDiscord: &fwilson_11"));
                sender.sendMessage(Utility.colourise("&8Usage: &a/kf help"));
            } else {
                sender.sendMessage("KFilter:");
                sender.sendMessage("Created by: Spud");
                sender.sendMessage("Discord: wilson_11");
                sender.sendMessage("Usage: kf help");
            }
            return true;
        }

        String subCommandName = args[0].toLowerCase();

        if (subCommands.containsKey(subCommandName)) {
            SubCommand subCommand = subCommands.get(subCommandName);
            List<String> permissions = subCommand.getPermissions();
            if (permissions != null && !hasAnyPermission(sender, permissions)) {
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&cError: &7You do not have permission to execute this command."));
                return true;
            }
            return subCommand.execute(sender, command, label, args);
        } else {
            if (sender instanceof Player) {
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&cUnknown subcommand: " + subCommandName));
                sender.sendMessage(Utility.colourise(plugin.PlayerPrefix + "&cTry &a/kf help"));
            } else {
                sender.sendMessage("Unknown subcommand: " + subCommandName);
                sender.sendMessage("Try: kf help");
            }
            return true;
        }
    }

    private boolean hasAnyPermission(CommandSender sender, List<String> permissions) {
        for (String permission : permissions) {
            if (sender.hasPermission(permission)) {
                return true;
            }
        }
        return false;
    }
}