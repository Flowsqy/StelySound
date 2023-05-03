package fr.flowsqy.stelysound.sound;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record SendableSound(@Nullable String permission, @NotNull SoundData soundData) {

    public boolean isTargeted(@NotNull Player player) {
        return permission == null || player.hasPermission(permission);
    }

    public void sendSound(@NotNull Player player) {
        player.playSound(player.getLocation(), soundData.sound(), soundData.volume(), soundData.pitch());
    }

    public void sendIfNeeded(@NotNull Player player) {
        if (isTargeted(player)) {
            sendSound(player);
        }
    }
}
