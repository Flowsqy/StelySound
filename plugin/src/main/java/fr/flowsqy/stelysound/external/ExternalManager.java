package fr.flowsqy.stelysound.external;

import fr.flowsqy.stelysound.external.bungeechatconnect.ExternalBCCLoader;
import fr.flowsqy.stelysound.external.essentialschat.ExternalEssentialsChatLoader;
import fr.flowsqy.stelysound.sound.SoundHandler;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ExternalManager {

    public void load(@NotNull Plugin plugin, @NotNull SoundHandler soundHandler) {
        final ExternalBCCLoader externalBBCLoader = new ExternalBCCLoader();
        externalBBCLoader.load(plugin, soundHandler);
        final ExternalEssentialsChatLoader externalEssentialsChatLoader = new ExternalEssentialsChatLoader();
        externalEssentialsChatLoader.load(plugin, soundHandler);
    }

}
