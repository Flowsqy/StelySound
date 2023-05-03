package fr.flowsqy.stelysound.listener;

import fr.flowsqy.stelysound.sound.SoundHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ChatListener implements Listener {

    private final Plugin plugin;
    private final SoundHandler soundHandler;

    public ChatListener(@NotNull Plugin plugin, @NotNull SoundHandler soundHandler) {
        this.plugin = plugin;
        this.soundHandler = soundHandler;
    }

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onChat(AsyncPlayerChatEvent event) {
        soundHandler.asyncHandle(plugin, Bukkit.getOnlinePlayers(), event.getMessage());
    }

}
