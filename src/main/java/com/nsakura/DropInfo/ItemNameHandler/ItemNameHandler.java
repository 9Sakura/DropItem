package com.nsakura.DropInfo.ItemNameHandler;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public interface ItemNameHandler {
    String getName(ItemStack itemStack);
    void setPlugin(JavaPlugin plugin);
}
