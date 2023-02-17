package betterfishing.betterfishing.managers;

import betterfishing.betterfishing.Main;
import betterfishing.betterfishing.objects.Fish;
import betterfishing.betterfishing.util.Difficulty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FishManager{

    private final Main main;
    String fishKey;
    private Fish fish;

    public FishManager(Main main){
        this.main = main;
    }

    //Generates fish from yaml
    public Fish generateFish(){
        Set<String> fishKeys = main.getFishFile().getKeys(false);
        List<String> fishList = new ArrayList<>(fishKeys);
        int random = new Random().nextInt(fishKeys.size());
        String type = fishList.get(random);
        this.fishKey = type;

        Fish fish = new Fish(type);
        this.fish = fish;
        double weight = generateWeight();
        weight = Math.round(weight);
        int length = generateSize();
        fish.setWeight(weight);
        fish.setSize(length);
        fish.setDifficulty(main.getFishFile().getString(fishKey + ".rarity"));
        fish.setBiomeList(generateBiomeList());
        fish.setItem(fishItem());

        return fish;
    }


    private List<Biome> generateBiomeList(){
        List<String> biomeStrings = main.getFishFile().getStringList(fishKey + ".biome");
        List<Biome> biomeList = new ArrayList<>();
        if (biomeStrings.isEmpty()){
            for (Biome biome : Biome.values()){
                biomeList.add(biome);
            }
        }
        for (int i=0;i < biomeStrings.size();i++){
            biomeList.add(Biome.valueOf(biomeStrings.get(i).toUpperCase()));
        }
        return biomeList;
    }

    private double generateWeight(){
        double weight;
        double weightMin = main.getFishFile().getDouble(fishKey + ".weight.min");
        double weightMax = main.getFishFile().getDouble(fishKey + ".weight.max");
        if (weightMin <= weightMax) {
            weight = new Random().nextDouble(weightMin, weightMax);
        } else {
            weight = 0.0;
        }
        return weight;
    }

    private int generateSize(){
        int size;
        int sizeMin = main.getFishFile().getInt(fishKey + ".length.min");
        int sizeMax = main.getFishFile().getInt(fishKey + ".length.max");
        if (sizeMin <= sizeMax){
            size = new Random().nextInt(sizeMin,sizeMax);
        } else {
            size = 1;
        }
        return size;
    }

    private ItemStack fishItem(){
        ItemStack fishItem = new ItemStack(Material.valueOf(main.getFishFile().getString(fishKey+".item.type").toUpperCase()));

        if (main.getFishFile().contains(fishKey + ".item.meta-data")) {
            ItemMeta itemMeta = fishItem.getItemMeta();
            String displayName = main.TCC(main.getFishFile().getString(fishKey + ".item.meta-data.display-name"));
            ArrayList<String> lore = (ArrayList<String>) main.getFishFile().getStringList(fishKey + ".item.meta-data.lore");
            int modelData = main.getFishFile().getInt(fishKey + ".item.meta-data.custom-model-data");
            for (int i = 0; i < lore.size(); i++) {
                String loreLn = lore.get(i);
                loreLn = loreLn.replaceAll("%weight%", String.valueOf(fish.getWeight()));
                loreLn = loreLn.replaceAll("%length%", String.valueOf(fish.getSize()));
                loreLn = loreLn.replaceAll("%rarity%", Difficulty.valueOf(fish.getDifficulty()).getDisplayName());
                lore.set(i, main.TCC(loreLn));
            }
              itemMeta.setDisplayName(displayName);
              itemMeta.setLore(lore);
              itemMeta.setCustomModelData(modelData);
              PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
              dataContainer.set(new NamespacedKey(main, "better-fishing"), PersistentDataType.STRING,"better-fishing");
              fishItem.setItemMeta(itemMeta);
        }
        return fishItem;
    }



    public boolean checkBiome(Biome biome){
        Boolean isBiome;
        while (!fish.getBiomeList().contains(biome)){
            generateFish();
            isBiome = false;
        }
        isBiome = true;
        return isBiome;
    }



}
