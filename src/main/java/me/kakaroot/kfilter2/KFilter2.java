package me.kakaroot.kfilter2;

import me.kakaroot.kfilter2.Commands.SubCommands.*;
import me.kakaroot.kfilter2.Commands.Structure.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class KFilter2 extends JavaPlugin {
    public final String PlayerPrefix = Utility.tcc("&6KFilter&f: &r");

    private EventHandler eventHandler;

    @Override
    public void onEnable() {
        // Event Handling
        this.eventHandler = new EventHandler(this);
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(eventHandler, this);

        // Commands
        BaseCommand baseCommand = new BaseCommand(this);
        baseCommand.registerSubCommand("add", new AddCommand(this));
        baseCommand.registerSubCommand("remove", new RemoveCommand(this));
        baseCommand.registerSubCommand("help", new HelpCommand());
        baseCommand.registerSubCommand("list", new ListCommand(this));
        baseCommand.registerSubCommand("reload",new ReloadCommand(this));
        baseCommand.registerSubCommand("clearchat", new ClearchatCommand(this));
        getCommand("kfilter").setExecutor(baseCommand);

        // Config File
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        reloadConfig();
        eventHandler.loadBannedWords();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public EventHandler getEventHandler() {
        return this.eventHandler;
    }
}