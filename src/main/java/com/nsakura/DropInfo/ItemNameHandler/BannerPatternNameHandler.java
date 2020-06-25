package com.nsakura.DropInfo.ItemNameHandler;

import com.nsakura.DropInfo.DropInfoPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BannerPatternNameHandler implements ItemNameHandler {
    private DropInfoPlugin plugin;
    private static BannerPatternNameHandler INSTANCE;
    private BannerPatternNameHandler() { }
    public static ItemNameHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BannerPatternNameHandler();
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
        String itemId = "item.minecraft." + id;
        String descId = "item.minecraft." + id + ".desc";
        return plugin.localizationMap.getOrDefault(descId, plugin.localizationMap.getOrDefault(itemId, id));
    }
}
