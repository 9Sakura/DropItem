package com.nsakura.DropInfo.ItemNameHandler;

import com.nsakura.DropInfo.DropInfoPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class PotionNameHandler implements ItemNameHandler {
    private DropInfoPlugin plugin;
    private static PotionNameHandler INSTANCE;
    private PotionNameHandler() { }
    public static ItemNameHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PotionNameHandler();
        }
        return INSTANCE;
    }

    private String getJSONEffect(PotionType effect) {
        switch (effect) {
            case AWKWARD:
            case FIRE_RESISTANCE:
            case INVISIBILITY:
            case LUCK:
            case MUNDANE:
            case NIGHT_VISION:
            case POISON:
            case SLOW_FALLING:
            case SLOWNESS:
            case STRENGTH:
            case THICK:
            case TURTLE_MASTER:
            case WATER:
            case WATER_BREATHING:
            case WEAKNESS:
                return effect.toString().toLowerCase();
            case INSTANT_DAMAGE:
                return "harming";
            case INSTANT_HEAL:
                return "healing";
            case JUMP:
                return "leaping";
            case REGEN:
                return "regeneration";
            case SPEED:
                return "swiftness";
            case UNCRAFTABLE:
                return "empty";
        }
        return null;
    }

    @Override
    public void setPlugin(JavaPlugin plugin) {
        this.plugin = (DropInfoPlugin)plugin;
    }

    @Override
    public String getName(ItemStack itemStack) {
        PotionData data = null;
        if (itemStack.hasItemMeta()) {
            if (itemStack.getItemMeta().hasDisplayName()) {
                return itemStack.getItemMeta().getDisplayName();
            }
            if (itemStack.getItemMeta().hasLocalizedName()) {
                return itemStack.getItemMeta().getLocalizedName();
            }
            data = ((PotionMeta)itemStack.getItemMeta()).getBasePotionData();
        }
        if (data != null) {
            String id = itemStack.getType().toString().toLowerCase();
            String effect = getJSONEffect(data.getType());
            String itemId = "item.minecraft." + id + ".effect." + effect;
            String blockId = "block.minecraft." + id + ".effect." + effect;
            String name = plugin.localizationMap.getOrDefault(itemId, plugin.localizationMap.getOrDefault(blockId, id + "." + effect));
            if (data.isUpgraded()) {
                name += " II";
            }
            if (data.isExtended()) {
                name += "+";
            }
            return name;
        }
        String id = itemStack.getType().toString().toLowerCase();
        String itemId = "item.minecraft." + id;
        String blockId = "block.minecraft." + id;
        return plugin.localizationMap.getOrDefault(itemId, plugin.localizationMap.getOrDefault(blockId, id));
    }
}
