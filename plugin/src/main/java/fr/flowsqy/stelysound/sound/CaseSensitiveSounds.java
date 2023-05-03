package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

public record CaseSensitiveSounds(boolean caseSensitive, @NotNull SendableSound[] sendableSounds) {

    public boolean isValid(@NotNull String reference, @NotNull String word) {
        return caseSensitive ? reference.equals(word) : reference.equalsIgnoreCase(word);
    }

}
