package com.nsakura.DropInfo.ItemNameHandler;

import com.nsakura.DropInfo.DropInfoPlugin;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.plugin.java.JavaPlugin;

public class ToolsNameHandler implements ItemNameHandler {
    private DropInfoPlugin plugin;
    private static ToolsNameHandler INSTANCE;
    private ToolsNameHandler() {

    }
    public static ItemNameHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ToolsNameHandler();
        }
        return INSTANCE;
    }

    @Override
    public void setPlugin(JavaPlugin plugin) {
        this.plugin = (DropInfoPlugin)plugin;
    }

    @Override
    public String getName(ItemStack itemStack) {
        String name = null;
        double durability = 0.0;
        if (itemStack.hasItemMeta()) {
            if (itemStack.getItemMeta().hasDisplayName()) {
                name = itemStack.getItemMeta().getDisplayName();
            }
            if (itemStack.getItemMeta().hasLocalizedName()) {
                name = itemStack.getItemMeta().getLocalizedName();
            }
            if (((Damageable)itemStack.getItemMeta()).hasDamage()) {
                double damage = ((Damageable)itemStack.getItemMeta()).getDamage();
                double maxDamage = itemStack.getType().getMaxDurability();
                if (damage < 0) {
                    damage = 0;
                } else if (damage > maxDamage) {
                    damage = maxDamage;
                }
                durability = damage / maxDamage * 100.0;
            }
        }
        if (name == null) {
            String id = itemStack.getType().toString().toLowerCase();
            String itemId = "item.minecraft." + id;
            String blockId = "block.minecraft." + id;
            name = plugin.localizationMap.getOrDefault(itemId, plugin.localizationMap.getOrDefault(blockId, id));
        }
        String durabilityString;
        if (itemStack.hasItemMeta() && itemStack.getItemMeta().isUnbreakable()) {
            durabilityString = ChatColor.GREEN + "∞%";
        } else {
            durability = 100.0 - durability;
            ChatColor color;
            if (durability >= 50.0) {
                color = ChatColor.GREEN;
            } else if (durability >= 20.0) {
                color = ChatColor.YELLOW;
            } else  {
                color = ChatColor.RED;
            }
            durabilityString = color + String.format("%.2f%%", durability);
        }
        return name + " " + durabilityString;
    }
}
