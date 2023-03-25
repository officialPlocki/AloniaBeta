package eu.alonia.aloniabeta;

import eu.alonia.aloniabeta.bot.DiscordBot;
import eu.alonia.aloniabeta.listener.PlayerListener;
import net.md_5.bungee.api.plugin.Plugin;

import javax.security.auth.login.LoginException;

public final class AloniaBeta extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            new DiscordBot().start();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }

        this.getProxy().getPluginManager().registerListener(this, new PlayerListener());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
