package betterfishing.betterfishing;

import betterfishing.betterfishing.events.FishingEvent;
import betterfishing.betterfishing.managers.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    //Config Initialization
    private FileManager config;
    private FileConfiguration c;

    //Fish Initialization
    private FileManager fishFileManager;
    private FileConfiguration fishFile;


    @Override
    public void onEnable() {
        // Plugin startup logic
        fishFileManager = new FileManager(this, "fish.yml");
        fishFile = fishFileManager.getFile();

        config = new FileManager(this, "config.yml");
        c = config.getFile();

        new FishingEvent(this);

        fishFileManager.setupFile();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public String TCC(String input){return ChatColor.translateAlternateColorCodes('&', input);}


    public FileConfiguration getFishFile(){return fishFile;}
    public FileManager getFileManager(){return fishFileManager;}


}
