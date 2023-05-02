package fr.flowsqy.stelysound.external.bungeechatconnect;

import fr.flowsqy.bungeechatconnect.event.BungeePlayerChatEvent;
import fr.flowsqy.stelysound.sound.SoundHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class ExternalBCCChatListener implements Listener {

    private final SoundHandler soundHandler;

    public ExternalBCCChatListener(@NotNull SoundHandler soundHandler) {
        this.soundHandler = soundHandler;
    }

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onChat(BungeePlayerChatEvent event) {
        soundHandler.handle(event.getMessage());
    }
}
