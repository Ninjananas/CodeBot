package fr.ninjananas.codebot.discord;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.Collection;
import java.util.Iterator;

public class CommandHandler {

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] argArray = event.getMessage().getContent().split(" ");

        if (argArray.length == 0 || (! argArray[0].startsWith(BotUtils.BOT_PREFIX)))
            return;

        String command = argArray[0].substring(BotUtils.BOT_PREFIX.length());

        switch (command) { // commands available everywhere
            case "bind":
                DiscordRunner.setChannelId(event.getChannel().getLongID());
                DiscordRunner.sendMessage("Bound to channel **" + event.getChannel().getName() + "**");
                break;
        }

        if (! event.getChannel().equals(DiscordRunner.getChannel())) return;

        switch (command) { // commands available only in bound channel
            case "online":
                Iterator<? extends Player> players = Bukkit.getServer().getOnlinePlayers().iterator();
                boolean comma = false;
                String pList = "";
                while (players.hasNext()) {
                    if (comma) {pList = pList + ", ";} else {comma = true;}
                    pList = pList + players.next().getName();
                }

                int nb = Bukkit.getServer().getOnlinePlayers().size();
                String s = "";
                if (nb == 0) {
                    DiscordRunner.sendMessage("Aucun joueur connecté");
                    break;
                }

                if (nb > 1) s = "s";

                DiscordRunner.sendMessage("**" + nb + "** joueur" + s + " connecté" + s + " : " + pList);
                break;
        }
    }
}
