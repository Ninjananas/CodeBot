package fr.ninjananas.codebot;

import fr.ninjananas.codebot.discord.DiscordRunner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.BroadcastMessageEvent;

public class Events implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        DiscordRunner.sendMessage("**" + event.getPlayer().getName() + ":** " + event.getMessage());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        DiscordRunner.sendMessage("`" + event.getPlayer().getName() + " s'est connecté`");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        DiscordRunner.sendMessage("`" + event.getPlayer().getName() + " s'est déconnecté`");
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        DiscordRunner.sendMessage("`" + event.getPlayer().getName() + " s'est fait expulser`");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        DiscordRunner.sendMessage("*" + event.getDeathMessage() + "*");
    }

    @EventHandler
    public void onBroadcast(BroadcastMessageEvent event) {
        DiscordRunner.sendMessage("***Broadcast** : " + event.getMessage() + "*");
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().startsWith("/me ")) {
            DiscordRunner.sendMessage("*" + event.getPlayer().getName() + event.getMessage().substring(3) + "*");
            return;
        }

        if (event.getMessage().startsWith("/say ")) {
            DiscordRunner.sendMessage(event.getPlayer().getName() + " dit : *" + event.getMessage().substring(5) + "*");
            return;
        }
    }
}
