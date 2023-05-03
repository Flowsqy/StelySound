package fr.flowsqy.stelysound.external.bungeechatconnect;

import fr.flowsqy.bungeechatconnect.BungeeChatConnectPlugin;
import fr.flowsqy.stelysound.sound.SoundHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

public class ExternalBCCLoader {

    public void load(@NotNull Plugin plugin, @NotNull SoundHandler soundHandler) {
        final Plugin bccPlugin = Bukkit.getPluginManager().getPlugin("BungeeChatConnect");
        if (bccPlugin == null || !bccPlugin.isEnabled()) {
            return;
        }
        if (!(bccPlugin instanceof BungeeChatConnectPlugin)) {
            return;
        }
        enable(plugin, soundHandler);
    }

    private void enable(@NotNull Plugin plugin, @NotNull SoundHandler soundHandler) {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        final ExternalBCCChatListener messageListener = new ExternalBCCChatListener(plugin, soundHandler);
        pluginManager.registerEvents(messageListener, plugin);
    }

}
