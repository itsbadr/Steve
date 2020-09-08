package com.steve;

import com.steve.Commands.Steve;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Const.loadData();
        new EventListener(this);
        getCommand("steve").setExecutor(new Steve());
        System.out.println("Enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("Disabled");
    }
}
