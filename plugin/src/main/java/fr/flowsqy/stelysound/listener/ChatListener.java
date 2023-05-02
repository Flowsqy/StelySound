package fr.flowsqy.stelysound.listener;

import fr.flowsqy.stelysound.sound.SoundHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final SoundHandler soundHandler;

    public ChatListener(SoundHandler soundHandler) {
        this.soundHandler = soundHandler;
    }

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onChat(AsyncPlayerChatEvent event) {
        soundHandler.handle(event.getMessage());
    }

}
