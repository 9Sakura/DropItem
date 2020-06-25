package com.nsakura.DropInfo;

import com.nsakura.DropInfo.ItemNameHandler.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

import java.util.logging.Logger;

class DropInfoListener implements Listener {
    private Logger logger = Logger.getLogger("Minecraft");

    DropInfoListener(DropInfoPlugin plugin) {
        BannerPatternNameHandler.getInstance().setPlugin(plugin);
        CommonItemNameHandler.getInstance().setPlugin(plugin);
        EnchantedBookNameHandler.getInstance().setPlugin(plugin);
        PotionNameHandler.getInstance().setPlugin(plugin);
        BookNameHandler.getInstance().setPlugin(plugin);
        MusicDiscNameHandler.getInstance().setPlugin(plugin);
        ToolsNameHandler.getInstance().setPlugin(plugin);
    }

    private String getEntityName(ItemStack itemStack) {
        String name;
        switch (ItemFilter.getType(itemStack)) {
            case BANNER_PATTERN:
                name = BannerPatternNameHandler.getInstance().getName(itemStack);
                break;
            case ENCHANTED_BOOK:
                name = EnchantedBookNameHandler.getInstance().getName(itemStack);
                break;
            case POTION:
                name = PotionNameHandler.getInstance().getName(itemStack);
                break;
            case WRITTEN_BOOK:
                name = BookNameHandler.getInstance().getName(itemStack);
                break;
            case MUSIC_DISC:
                name = MusicDiscNameHandler.getInstance().getName(itemStack);
                break;
            case TOOLS:
            case WEAPON:
            case EQUIPMENT:
                name = ToolsNameHandler.getInstance().getName(itemStack);
                break;
            default:
                name = CommonItemNameHandler.getInstance().getName(itemStack);
        }
        return name;
    }

    @EventHandler
    public void onItemMerge(ItemMergeEvent event) {
        ItemStack entity = event.getEntity().getItemStack();
        ItemStack target = event.getTarget().getItemStack();
        String name = getEntityName(entity);
        event.getTarget().setCustomName(name + "×" + (entity.getAmount() + target.getAmount()));
        event.getTarget().setCustomNameVisible(true);
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        ItemStack itemStack = event.getEntity().getItemStack();
        String name = getEntityName(itemStack);
        if (itemStack.getAmount() != 1) {
            event.getEntity().setCustomName(name + ChatColor.translateAlternateColorCodes('&', "&r×") + itemStack.getAmount());
        } else {
            event.getEntity().setCustomName(name);
        }
        event.getEntity().setCustomNameVisible(true);
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        ItemStack itemStack = event.getItem().getItemStack();
        String name = getEntityName(itemStack);
        if (event.getRemaining() != 1) {
            event.getItem().setCustomName(name + ChatColor.translateAlternateColorCodes('&', "&r×") + event.getRemaining());
        } else {
            event.getItem().setCustomName(name);
        }
        event.getEntity().setCustomNameVisible(true);
    }
}
