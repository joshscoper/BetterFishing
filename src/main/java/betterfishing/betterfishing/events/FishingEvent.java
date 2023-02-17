package betterfishing.betterfishing.events;

import betterfishing.betterfishing.Main;
import betterfishing.betterfishing.managers.FishManager;
import betterfishing.betterfishing.objects.Fish;
import betterfishing.betterfishing.util.Difficulty;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class FishingEvent implements Listener {

    private final Main main;
    private final Set<UUID> fishing = new HashSet<>();
    private static final Random random = new Random();
    private FishManager fishManager;

    private FishHook hook;
    private Player player;
    private Fish caught;

    public FishingEvent(Main main){
        this.main = main;
        fishManager = new FishManager(main);
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onFish(PlayerFishEvent event){
        this.player = event.getPlayer();
        this.hook = event.getHook();
        if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)){
            this.caught = fishManager.generateFish();
            event.setCancelled(true);
            lootFish();
        }
    }


    public void lootFish(){
        Item item = hook.getWorld().dropItemNaturally(hook.getLocation(),caught.getItem());
        Vector vec = player.getLocation().subtract(hook.getLocation()).toVector();
        vec.setY(vec.getY() * .020 + vec.length() * .04);
        vec.setX(vec.getX() * .08);
        vec.setZ(vec.getZ() * .08);
        item.setVelocity(vec);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 0);
        hook.remove();
    }

}
