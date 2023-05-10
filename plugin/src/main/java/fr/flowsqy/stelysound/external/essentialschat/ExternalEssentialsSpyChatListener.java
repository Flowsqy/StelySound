package fr.flowsqy.stelysound.external.essentialschat;

import fr.flowsqy.stelysound.sound.SoundHandler;
import net.ess3.api.events.LocalChatSpyEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ExternalEssentialsSpyChatListener implements Listener {

    private final Plugin plugin;
    private final SoundHandler soundHandler;

    public ExternalEssentialsSpyChatListener(@NotNull Plugin plugin, @NotNull SoundHandler soundHandler) {
        this.plugin = plugin;
        this.soundHandler = soundHandler;
    }

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.MONITOR)
    private void onChat(LocalChatSpyEvent event) {
        soundHandler.asyncHandle(plugin, event.getRecipients(), event.getMessage());
    }

}
