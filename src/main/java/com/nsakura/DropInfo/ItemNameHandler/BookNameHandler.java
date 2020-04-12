package com.nsakura.DropInfo.ItemNameHandler;

import com.nsakura.DropInfo.DropInfoPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class BookNameHandler implements ItemNameHandler {
    private DropInfoPlugin plugin;
    private static BookNameHandler INSTANCE;
    private BookNameHandler() { }
    public static ItemNameHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookNameHandler();
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
            if (((BookMeta)itemStack.getItemMeta()).hasTitle()) {
                return ((BookMeta)itemStack.getItemMeta()).getTitle();
            }
        }
        String id = itemStack.getType().toString().toLowerCase();
        String itemId = "item.minecraft." + id;
        String blockId = "block.minecraft." + id;
        return plugin.localizationMap.getOrDefault(itemId, plugin.localizationMap.getOrDefault(blockId, id));
    }
}
