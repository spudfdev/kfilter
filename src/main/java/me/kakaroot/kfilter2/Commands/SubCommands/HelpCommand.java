package me.kakaroot.kfilter2.Commands.SubCommands;

import me.kakaroot.kfilter2.Commands.CommandStructure.SubCommand;
import me.kakaroot.kfilter2.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpCommand implements SubCommand {

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        sendHelpMessage(sender);
        return true;
    }

    private void sendHelpMessage(CommandSender sender) {
        if (sender instanceof Player) {
            sender.sendMessage(Utility.colourise("\n &a---- &6KFilter &a----"));
            sender.sendMessage(Utility.colourise("&a/kf add <word> <replacement> &7- Adds a word to the filter with its replacement &4[kf.add]"));
            sender.sendMessage(Utility.colourise("&a/kf remove <word> &7- Removes a word from the filter &4[kf.remove]"));
            sender.sendMessage(Utility.colourise("&a/kf list &7- Lists all filtered words &4[kf.list]"));
            sender.sendMessage(Utility.colourise("&a/kf reload &7- Reloads the config file &4[kf.reload]"));
            sender.sendMessage(Utility.colourise("&a/kf clearchat &7- Clears the chat &4[kf.clearchat]"));


        } else {
            sender.sendMessage("---- KFilter ----");
            sender.sendMessage("kf add <word> <replacement> - Adds a word to the filter with its replacement [kf.add]");
            sender.sendMessage("kf remove <word> - Removes a word from the filter [kf.remove]");
            sender.sendMessage("kf list - Lists all filtered words [kf.list]");
            sender.sendMessage("kf reload - Reloads the config file [kf.reload]");
            sender.sendMessage("kf clearchat - Clears the chat [kf.clearchat]");
        }
    }

    @Override
    public List<String> getPermissions() {
        return null;
    }
}
