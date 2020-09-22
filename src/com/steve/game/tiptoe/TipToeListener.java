package com.steve.game.tiptoe;

import com.steve.Main;
import com.steve.PlayerData;
import com.steve.Util;
import com.steve.game.GameManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.ChatColor.*;
import static org.bukkit.GameMode.CREATIVE;
import static org.bukkit.GameMode.SPECTATOR;

public class TipToeListener implements Listener {
    final TipToeGame game;

    public TipToeListener(TipToeGame game) {
        this.game = game;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        //  e.getPlayer().sendMessage("moved");
        Location pos = e.getTo();
        if (pos == null) return;
        pos = pos.clone();
        Block b = pos.subtract(0,1,0).getBlock();

        for (ArrayList<Block> platform : game.platformList) {
            if (platform.contains(b)) { // if true, remove entire platform
                for (Block fakeBlock : platform) {
                    Material blockMaterial = fakeBlock.getType();
                    fakeBlock.setType(Material.AIR);
                    Location blockPos = fakeBlock.getLocation().clone();
                    World w = fakeBlock.getWorld();
                    FallingBlock fallingBlock = w.spawnFallingBlock(blockPos.add(.5, 0 ,.5),
                            Bukkit.createBlockData(blockMaterial));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, fallingBlock::remove, 20);
                }
                game.platformList.remove(platform);
                break;
            }

            // causes memory leak or is too intensive?
            // Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> game.createFakePlatform(platform.get(0).getLocation()), 60);
        }

        // p.sendMessage("Block = " + b.getBlockData().getAsString());
    }
}