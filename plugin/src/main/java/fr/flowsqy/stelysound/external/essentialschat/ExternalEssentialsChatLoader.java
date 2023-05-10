package fr.flowsqy.stelysound.external.essentialschat;

import com.earth2me.essentials.chat.EssentialsChat;
import fr.flowsqy.stelysound.sound.SoundHandler;
import net.ess3.api.IEssentials;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ExternalEssentialsChatLoader {

    public void load(@NotNull Plugin plugin, @NotNull SoundHandler soundHandler) {
        final Plugin essentialChatPlugin = Bukkit.getPluginManager().getPlugin("EssentialsChat");
        if (essentialChatPlugin == null || !essentialChatPlugin.isEnabled()) {
            return;
        }
        if (!(essentialChatPlugin instanceof EssentialsChat)) {
            return;
        }
        enable(plugin, soundHandler);
    }

    private void enable(@NotNull Plugin plugin, @NotNull SoundHandler soundHandler) {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        final IEssentials essentialsPlugin = (IEssentials) pluginManager.getPlugin("Essentials");
        Objects.requireNonNull(essentialsPlugin);
        final ExternalEssentialsSpyChatListener spyChatListener = new ExternalEssentialsSpyChatListener(plugin, soundHandler);
        pluginManager.registerEvents(spyChatListener, plugin);
    }

}
