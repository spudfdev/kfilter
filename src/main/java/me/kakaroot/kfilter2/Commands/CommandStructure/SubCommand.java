package me.kakaroot.kfilter2.Commands.CommandStructure;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface SubCommand {
    boolean execute(CommandSender sender, Command command, String label, String[] args);
    List<String> getPermissions();
}
