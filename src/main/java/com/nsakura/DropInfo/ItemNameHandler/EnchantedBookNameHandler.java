package com.nsakura.DropInfo.ItemNameHandler;

import com.nsakura.DropInfo.DropInfoPlugin;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class EnchantedBookNameHandler implements ItemNameHandler {
    private DropInfoPlugin plugin;
    private static EnchantedBookNameHandler INSTANCE;
    private EnchantedBookNameHandler() { }
    public static ItemNameHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EnchantedBookNameHandler();
        }
        return INSTANCE;
    }

    @Override
    public void setPlugin(JavaPlugin plugin) {
        this.plugin = (DropInfoPlugin)plugin;
    }

    @Override
    public String getName(ItemStack itemStack) {
        Map<Enchantment, Integer> enchantments = null;
        if (itemStack.hasItemMeta()) {
            if (itemStack.getItemMeta().hasDisplayName()) {
                return itemStack.getItemMeta().getDisplayName();
            }
            if (itemStack.getItemMeta().hasLocalizedName()) {
                return itemStack.getItemMeta().getLocalizedName();
            }
            if (((EnchantmentStorageMeta)itemStack.getItemMeta()).hasStoredEnchants()) {
                enchantments = ((EnchantmentStorageMeta)itemStack.getItemMeta()).getStoredEnchants();
            }
        }
        if (enchantments != null && !enchantments.isEmpty()) {
            StringBuilder name = new StringBuilder();
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                if (name.length() > 1) {
                    name.append("+");
                }
                String id = entry.getKey().getKey().getKey();
                String enchantment = plugin.localizationMap.getOrDefault("enchantment.minecraft." + id, id);
                String level = plugin.localizationMap.getOrDefault("enchantment.level." + entry.getValue(), entry.getValue().toString());
                name.append(enchantment).append(" ").append(level);
            }
            return name.toString();
        }
        String id = itemStack.getType().toString().toLowerCase();
        String itemId = "item.minecraft." + id;
        String blockId = "block.minecraft." + id;
        return plugin.localizationMap.getOrDefault(itemId, plugin.localizationMap.getOrDefault(blockId, id));
    }
}
