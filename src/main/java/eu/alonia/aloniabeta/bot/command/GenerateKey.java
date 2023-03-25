package eu.alonia.aloniabeta.bot.command;

import eu.alonia.aloniabeta.util.KeyHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GenerateKey extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("generatekey")) {
            if(event.getUser().getIdLong() == 470300602677723136L) {
                event.reply("Key: " + new KeyHandler().generateKey()).setEphemeral(true).queue();
            } else {
                event.reply("You don't have the permission to use this command!").setEphemeral(true).queue();
            }
        }
    }

}
