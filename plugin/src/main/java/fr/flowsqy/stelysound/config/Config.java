package fr.flowsqy.stelysound.config;

import fr.flowsqy.stelysound.sound.ConfiguredSound;
import fr.flowsqy.stelysound.sound.SendableSound;
import fr.flowsqy.stelysound.sound.SoundData;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Config {

    private YamlConfiguration configuration;

    public void load(ConfigLoader configLoader, JavaPlugin javaPlugin, String fileName) {
        configuration = configLoader.initFile(javaPlugin.getDataFolder(), Objects.requireNonNull(javaPlugin.getResource(fileName)), fileName);
    }

    public char @NotNull [] getSeparators() {
        final List<String> charList = configuration.getStringList("separators").stream().filter(s -> s.length() == 1).toList();
        final char[] separators = new char[charList.size()];
        int i = 0;
        for (String c : charList) {
            separators[i++] = c.charAt(0);
        }
        return separators;
    }

    @NotNull
    public List<ConfiguredSound> getConfiguredSounds(@NotNull Logger logger) {
        final ConfigurationSection soundsSection = configuration.getConfigurationSection("sounds");
        if (soundsSection == null) {
            return Collections.emptyList();
        }
        final List<ConfiguredSound> configuredSounds = new LinkedList<>();
        for (final String key : soundsSection.getKeys(false)) {
            final ConfigurationSection soundSection = soundsSection.getConfigurationSection(key);
            if (soundSection == null) {
                continue;
            }
            final List<String> words;
            final SendableSound sendableSound;
            try {
                words = getWords(soundSection);
                final SoundData soundData = getSoundData(soundSection);
                sendableSound = getSendableSound(soundSection, soundData);
            } catch (IllegalArgumentException e) {
                logger.warning(e.getMessage());
                continue;
            }
            final boolean caseSensitive = soundSection.getBoolean("case-sensitive");
            final boolean triggeredByNames = soundSection.getBoolean("triggered-by-names");
            if (!triggeredByNames && words.isEmpty()) {
                logger.warning("'" + key + "' sound can't be triggered");
                continue;
            }
            configuredSounds.add(new ConfiguredSound(caseSensitive, triggeredByNames, sendableSound, words));
        }
        return configuredSounds;
    }

    @NotNull
    private List<String> getWords(@NotNull ConfigurationSection soundSection) {
        List<String> words = soundSection.getStringList("words");
        words = words.stream().map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        return words;
    }

    @NotNull
    private SendableSound getSendableSound(@NotNull ConfigurationSection soundSection, @NotNull SoundData soundData) {
        String permission = soundSection.getString("permission");
        if (permission != null) {
            permission = permission.trim();
            if (permission.isEmpty()) {
                permission = null;
            }
        }
        return new SendableSound(permission, soundData);
    }

    @NotNull
    private SoundData getSoundData(@NotNull ConfigurationSection soundSection) throws IllegalArgumentException {
        final Sound sound = Sound.valueOf(soundSection.getString("sound"));
        final float volume = (float) soundSection.getDouble("volume");
        final float pitch = (float) soundSection.getDouble("pitch");

        return new SoundData(sound, volume, pitch);
    }

}
