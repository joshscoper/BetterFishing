package betterfishing.betterfishing.objects;

import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Fish {
    String type;
    int size;
    double weight;
    String rarity;
    ItemStack item;
    List<Biome> biomeList;

    public Fish(String type){
        this.type = type;
    }

    public String getType(){return type;}

    public int getSize(){return size;}

    public void setSize(int size){
        this.size = size;
    }

    public double getWeight(){return weight;}

    public void setWeight(double weight){
        this.weight = weight;
    }

    public String getDifficulty(){return rarity;}

    public void setDifficulty(String difficulty){
        this.rarity = difficulty;
    }

    public ItemStack getItem(){return item;}

    public void setItem(ItemStack item){
        this.item = item;
    }

    public List<Biome> getBiomeList(){return biomeList;}

    public void setBiomeList(List biomeList){this.biomeList = biomeList;}

}
