package fr.ninjananas.codebot.discord;

import org.bukkit.plugin.java.JavaPlugin;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;

public class DiscordRunner {
    private static IDiscordClient cli = null;
    private static long id = 0;
    private static JavaPlugin plugin = null;

    public static void setChannelId(long id) {
        DiscordRunner.id = id;
        plugin.getConfig().set("IDchannel", id);
        plugin.saveConfig();
    }

    public static IChannel getChannel() {
        return cli.getChannelByID(id);
    }

    public static void init(JavaPlugin plugin) {
        DiscordRunner.plugin = plugin;

        String token = plugin.getConfig().getString("token", null);
        long channelId = plugin.getConfig().getLong("IDchannel", 0);

        if (token == null) {
            //TODO : Log error no token
            return;
        }
        cli = BotUtils.getBuiltDiscordClient(token);
        cli.getDispatcher()
                .registerListeners(new Events(), new CommandHandler());
        cli.login();

        if (channelId == 0) {
            //TODO : Log warning no channel Id
        } else {
            setChannelId(channelId);
        }

    }

    public static void idle() {
        cli.idle();
    }

    public static void online() {
        cli.online();
    }

    public static void playingText(String s) {
        cli.changePlayingText(s);
    }

    public static void sendMessage(String s) {
        IChannel channel = DiscordRunner.getChannel();
        if (channel == null) return;
        BotUtils.sendMessage(channel, s);
    }
}
