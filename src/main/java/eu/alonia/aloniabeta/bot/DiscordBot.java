package eu.alonia.aloniabeta.bot;

import eu.alonia.aloniabeta.bot.command.GenerateKey;
import eu.alonia.aloniabeta.bot.command.RedeemKey;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    public void start() throws LoginException {
        JDABuilder builder = JDABuilder.createDefault("TOKEN");

        builder.setActivity(Activity.competing("against you"));

        builder.addEventListeners(new GenerateKey());
        builder.addEventListeners(new RedeemKey());

        JDA jda = builder.build();

        jda.upsertCommand("redeemkey", "Redeem your Beta Key").queue();
        jda.upsertCommand("generatekey", "Generate a Beta Key").queue();
    }

}
