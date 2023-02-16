package betterfishing.betterfishing.util;

import betterfishing.betterfishing.Main;

public enum Difficulty {

    COMMON(2,Main.getPlugin(Main.class).getConfig().getString("config.difficulty.common")),
    UNCOMMON(3,Main.getPlugin(Main.class).getConfig().getString("config.difficulty.uncommon")),
    RARE(4,Main.getPlugin(Main.class).getConfig().getString("config.difficulty.rare")),
    EPIC(5,Main.getPlugin(Main.class).getConfig().getString("config.difficulty.epic")),
    LEGENDARY(6,Main.getPlugin(Main.class).getConfig().getString("config.difficulty.legendary"));

    private int requiredClicks;
    private String displayName;

    Difficulty(int requiredClicks,String displayName){this.requiredClicks=requiredClicks;this.displayName=displayName;}

    public int getRequiredClicks(){return requiredClicks;}
    public String getDisplayName(){return displayName;}




}
