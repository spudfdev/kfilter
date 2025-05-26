package me.kakaroot.kfilter2;

import me.kakaroot.kfilter2.Commands.SubCommands.*;
import me.kakaroot.kfilter2.Commands.CommandStructure.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class KFilter2 extends JavaPlugin {
    public final String PlayerPrefix = Utility.colourise("&6KFilter&f: &r");

    private ChatListener chatListener;

    @Override
    public void onEnable() {
        this.chatListener = new ChatListener(this);
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(chatListener, this);
        registerCommands();
        loadConfig();
    }

    private void registerCommands() {
        BaseCommand baseCommand = new BaseCommand(this);
        baseCommand.registerSubCommand("add", new AddCommand(this));
        baseCommand.registerSubCommand("remove", new RemoveCommand(this));
        baseCommand.registerSubCommand("help", new HelpCommand());
        baseCommand.registerSubCommand("list", new ListCommand(this));
        baseCommand.registerSubCommand("reload",new ReloadCommand(this));
        baseCommand.registerSubCommand("clearchat", new ClearChatCommand(this));
        this.getCommand("kfilter").setExecutor(baseCommand);
    }

    private void loadConfig() {
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();
        reloadConfig();
        chatListener.loadBannedWords();
    }

    public ChatListener getListener() {
        return this.chatListener;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}