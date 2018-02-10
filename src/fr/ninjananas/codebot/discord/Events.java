package fr.ninjananas.codebot.discord;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.shard.DisconnectedEvent;
import sx.blah.discord.handle.impl.events.shard.LoginEvent;

import java.util.Iterator;

public class Events {

    @EventSubscriber
    public void onLogin(LoginEvent event) {
        DiscordRunner.playingText("Bande de peds");
    }

    @EventSubscriber
    public void onDisconnect(DisconnectedEvent event) {
    }

    // Messages Discord -> Minecraft
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        if ( (!event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX))
                && event.getChannel().equals(DiscordRunner.getChannel())) {
              Iterator<? extends Player> players = Bukkit.getServer().getOnlinePlayers().iterator();
              while (players.hasNext()) {
                  players.next().sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + event.getMessage().getAuthor().getName() + ChatColor.WHITE + "] " + event.getMessage().getContent());
              }
        }
    }
}
