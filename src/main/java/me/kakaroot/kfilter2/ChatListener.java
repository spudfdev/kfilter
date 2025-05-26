package me.kakaroot.kfilter2;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

public class ChatListener implements Listener {
    private KFilter2 plugin;
    private Map<String, String> bannedWordsMap;

    public ChatListener(KFilter2 inst) {
        this.plugin = inst;
        this.bannedWordsMap = new HashMap<>();
        loadBannedWords();
    }

    public void loadBannedWords() {
        bannedWordsMap = new HashMap<>();
        FileConfiguration configFile = plugin.getConfig();
        ConfigurationSection bannedWordsSection = configFile.getConfigurationSection("banned_words");
        if (bannedWordsSection != null) {
            for (String key : bannedWordsSection.getKeys(false)) {
                String replacement = bannedWordsSection.getString(key);
                if (replacement != null) {
                    bannedWordsMap.put(key.toLowerCase(), replacement);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();

        for (Map.Entry<String, String> entry : bannedWordsMap.entrySet()) {
            String bannedWord = entry.getKey();
            String replacement = entry.getValue();
            message = message.replaceAll("(?i)" + bannedWord, replacement);
        }
        e.setMessage(message);
    }

}
