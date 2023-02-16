package betterfishing.betterfishing.events;

import betterfishing.betterfishing.Main;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FishingEvent implements Listener {

    private final Main main;
    private final Set<UUID> fishing = new HashSet<>();

    public FishingEvent(Main main){
        this.main = main;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void a(PlayerFishEvent event){
        Player player = event.getPlayer();
        FishHook hook = event.getHook();
    }
}
