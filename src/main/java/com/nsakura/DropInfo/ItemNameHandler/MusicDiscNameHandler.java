package com.nsakura.DropInfo.ItemNameHandler;

import com.nsakura.DropInfo.DropInfoPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MusicDiscNameHandler implements ItemNameHandler {
    private DropInfoPlugin plugin;
    private static MusicDiscNameHandler INSTANCE;
    private MusicDiscNameHandler() { }
    public static ItemNameHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MusicDiscNameHandler();
        }
        return INSTANCE;
    }

    @Override
    public void setPlugin(JavaPlugin plugin) {
        this.plugin = (DropInfoPlugin)plugin;
    }

    @Override
    public String getName(ItemStack itemStack) {
        if (itemStack.hasItemMeta()) {
            if (itemStack.getItemMeta().hasDisplayName()) {
                return itemStack.getItemMeta().getDisplayName();
            }
            if (itemStack.getItemMeta().hasLocalizedName()) {
                return itemStack.getItemMeta().getLocalizedName();
            }
        }
        String id = itemStack.getType().toString().toLowerCase();
        String itemId = "item.minecraft." + id + ".desc";
        String blockId = "block.minecraft." + id + ".desc";
        return plugin.localizationMap.getOrDefault(itemId, plugin.localizationMap.getOrDefault(blockId, id));
    }

}
