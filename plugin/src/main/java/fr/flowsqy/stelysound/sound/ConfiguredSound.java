package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record ConfiguredSound(boolean caseSensitive, boolean triggeredByNames, @NotNull SendableSound sendableSound,
                              List<String> words) {

}
