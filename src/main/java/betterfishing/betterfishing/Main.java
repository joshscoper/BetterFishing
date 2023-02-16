package betterfishing.betterfishing;

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
    private File config;
    private FileConfiguration c;

    //Fish Initialization
    private FileManager fishFileManager;
    private FileConfiguration fishFile;


    @Override
    public void onEnable() {
        // Plugin startup logic
        fishFileManager = new FileManager(this, "fish.yml");
        fishFile = fishFileManager.getFile();


        fishFileManager.setupFile();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    //Config
    public FileConfiguration config() {
        return this.c;
    }

    public void loadConfig(){
        this.config = new File(this.getDataFolder(), "config.yml");
        if (!this.config.exists()) {
            this.config.getParentFile().mkdirs();
            this.saveResource("config.yml", false);
        }

        this.c = new YamlConfiguration();

        try {
            this.c.load(this.config);
        } catch (InvalidConfigurationException | IOException var2) {
            var2.printStackTrace();
        }
    }

    public void saveFile(FileConfiguration fileconfig, File file) {
        try {
            fileconfig.save(file);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public String TCC(String input){return ChatColor.translateAlternateColorCodes('&', input);}


    public FileConfiguration getFishFile(){return fishFile;}
    public FileManager getFileManager(){return fishFileManager;}


}
