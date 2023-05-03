package fr.flowsqy.stelysound.sound;

import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

public record SoundData(@NotNull Sound sound, float volume, float pitch) {


}
