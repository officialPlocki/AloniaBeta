package eu.alonia.aloniabeta.bot.command;

import eu.alonia.aloniabeta.util.KeyHandler;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

public class RedeemKey extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("redeemkey")) {
            event.
                    reply("Please get your BetaKey and your Minecraft UUID ready.\n" +
                            "You can get your UUID by entering your Username on **https://mcuuid.net/**.\n" +
                            "Copy the UUID under \"Full UUID\" and use it to activate your Key.\n" +
                            "\n" +
                            "Please check if the player already has access to the Beta Network.\n" +
                            "This system will not check if the player has access.")
                    .setEphemeral(true)
                    .addActionRow(Button.link("https://mcuuid.net/", "Get UUID"))
                    .addActionRow(Button.success("key_redeem", "Activate Key"))
                    .queue();
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if(event.getComponentId().equalsIgnoreCase("key_redeem")) {
            Modal modal = Modal.create("key_redeem", "Enter Key and Minecraft UUID")
                    .addActionRow(TextInput.create("key", "Beta Key", TextInputStyle.SHORT).build())
                    .addActionRow(TextInput.create("uuid", "Minecraft UUID", TextInputStyle.SHORT).build())
                    .build();

            event.replyModal(modal).queue();
        }
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if(event.getModalId().equalsIgnoreCase("key_redeem")) {
            if(new KeyHandler().isUsed(event.getInteraction().getValue("key").getAsString())) {
                event.reply("This Key is already used or doesn't exist!").setEphemeral(true).queue();
            } else {
                if(new KeyHandler().setOwner(event.getInteraction().getValue("key").getAsString(), event.getInteraction().getValue("uuid").getAsString())) {
                    event.reply("Key redeemed!").setEphemeral(true).queue();
                } else {
                    event.reply("An error occurred!").setEphemeral(true).queue();
                }
            }
        }
    }
}
