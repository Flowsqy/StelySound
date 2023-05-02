package fr.flowsqy.stelysound.external;

import fr.flowsqy.stelysound.external.bungeechatconnect.ExternalBCCLoader;
import fr.flowsqy.stelysound.sound.SoundHandler;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ExternalManager {

    public void load(@NotNull Plugin plugin, @NotNull SoundHandler soundHandler) {
        final ExternalBCCLoader essentialsBBCLoader = new ExternalBCCLoader();
        essentialsBBCLoader.load(plugin, soundHandler);
    }

}
