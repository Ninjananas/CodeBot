package fr.ninjananas.codebot;

import fr.ninjananas.codebot.discord.DiscordRunner;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import sx.blah.discord.util.DiscordException;

public final class Main extends JavaPlugin {

    FileConfiguration config = this.getConfig();
    DiscordRunner discord = null;

    @Override
    public void onEnable() {
        config.addDefault("token", "token_here");
        config.addDefault("IDchannel", 0);
        config.options().copyDefaults(true);
        saveConfig();

        DiscordRunner.init(this);

        getServer().getPluginManager().registerEvents(new Events(), this);

    }

    @Override
    public void onDisable() {

    }
}
