package com.steve;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Platform {
    int width;
    int length;
    Location position;
    Material material;
    boolean isFake;
    ArrayList<Block> tileBlocks;

    public Platform(int width, int length, Location position, Material material, boolean isFake){
        this.width = width;
        this.length = length;
        this.position = position;
        this.material = material;
        this.isFake = isFake;
    }

    public void placeBlocks(){
        World w = position.getWorld();
        for (int x = position.getBlockX(); x < position.getBlockX()+width; x++) {
            for (int z = position.getBlockZ(); z < position.getBlockZ()+length; z++) {
                Location l = new Location(w,x,position.getBlockY(),z);
                l.getBlock().setType(material);
            }
        }
    }


}
