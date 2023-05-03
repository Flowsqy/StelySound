package fr.flowsqy.stelysound;

import fr.flowsqy.stelysound.config.Config;
import fr.flowsqy.stelysound.config.ConfigLoader;
import fr.flowsqy.stelysound.external.ExternalManager;
import fr.flowsqy.stelysound.listener.ChatListener;
import fr.flowsqy.stelysound.sound.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class StelySoundPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final Logger logger = getLogger();
        final File dataFolder = getDataFolder();
        final ConfigLoader configLoader = new ConfigLoader();

        if (!configLoader.checkDataFolder(dataFolder)) {
            logger.log(Level.WARNING, "Can not write in the directory : " + dataFolder.getAbsolutePath());
            logger.log(Level.WARNING, "Disable the plugin");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        final Config config = new Config();
        config.load(configLoader, this, "config.yml");

        final char[] separators = config.getSeparators();
        final List<ConfiguredSound> configuredSounds = config.getConfiguredSounds(logger);

        final WordsCollector wordsCollector = new WordsCollector(separators);
        final SoundsOrganizer soundsOrganizer = new SoundsOrganizer();
        final OrganizedSounds organizedSounds = soundsOrganizer.organize(configuredSounds);
        final SoundHandler soundHandler = new SoundHandler(wordsCollector, organizedSounds);

        final ExternalManager externalManager = new ExternalManager();
        externalManager.load(this, soundHandler);
        final ChatListener chatListener = new ChatListener(this, soundHandler);
        Bukkit.getPluginManager().registerEvents(chatListener, this);
    }
}
