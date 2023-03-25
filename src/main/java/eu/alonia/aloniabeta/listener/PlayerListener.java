package eu.alonia.aloniabeta.listener;

import eu.alonia.aloniabeta.util.KeyHandler;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        if(!new KeyHandler().hasKey(event.getPlayer().getUniqueId().toString())) {
            event.getPlayer().disconnect(TextComponent.fromLegacyText("§cYou don't have a key! Join our discord to redeem one!\n\n§bhttps://discord.gg/S4fCd44F9R"));
        } else {
            event.getPlayer().sendMessage(TextComponent.fromLegacyText("§a§lWelcome to the Alonia Beta!\n\n§7If you notice any bugs or errors, please report it on our discord!"));
        }
    }

}
