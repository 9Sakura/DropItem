package com.nsakura.DropInfo;

import org.bukkit.inventory.ItemStack;

public enum ItemFilter {
    REGULAR,
    ENCHANTED_BOOK,
    POTION,
    WRITTEN_BOOK,
    MUSIC_DISC,
    TOOLS,
    WEAPON,
    EQUIPMENT;

    public static ItemFilter getType(ItemStack itemStack) {
        switch (itemStack.getType()) {
            case ENCHANTED_BOOK:
                return ENCHANTED_BOOK;
            case POTION:
            case SPLASH_POTION:
            case LINGERING_POTION:
            case TIPPED_ARROW:
                return POTION;
            case WRITTEN_BOOK:
                return WRITTEN_BOOK;
            case MUSIC_DISC_13:
            case MUSIC_DISC_CAT:
            case MUSIC_DISC_BLOCKS:
            case MUSIC_DISC_CHIRP:
            case MUSIC_DISC_FAR:
            case MUSIC_DISC_MALL:
            case MUSIC_DISC_MELLOHI:
            case MUSIC_DISC_STAL:
            case MUSIC_DISC_STRAD:
            case MUSIC_DISC_WARD:
            case MUSIC_DISC_11:
            case MUSIC_DISC_WAIT:
                return MUSIC_DISC;
            case TURTLE_HELMET:
            case IRON_SHOVEL:
            case IRON_PICKAXE:
            case IRON_AXE:
            case FLINT_AND_STEEL:
            case WOODEN_SHOVEL:
            case WOODEN_PICKAXE:
            case WOODEN_AXE:
            case STONE_SHOVEL:
            case STONE_PICKAXE:
            case STONE_AXE:
            case DIAMOND_SHOVEL:
            case DIAMOND_PICKAXE:
            case DIAMOND_AXE:
            case GOLDEN_SHOVEL:
            case GOLDEN_PICKAXE:
            case GOLDEN_AXE:
            case WOODEN_HOE:
            case STONE_HOE:
            case IRON_HOE:
            case DIAMOND_HOE:
            case GOLDEN_HOE:
            case FISHING_ROD:
            case SHEARS:
                return TOOLS;
            case BOW:
            case IRON_SWORD:
            case WOODEN_SWORD:
            case STONE_SWORD:
            case DIAMOND_SWORD:
            case GOLDEN_SWORD:
            case TRIDENT:
            case CROSSBOW:
                return WEAPON;
            case ELYTRA:
            case LEATHER_HELMET:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
            case CHAINMAIL_HELMET:
            case CHAINMAIL_CHESTPLATE:
            case CHAINMAIL_LEGGINGS:
            case CHAINMAIL_BOOTS:
            case IRON_HELMET:
            case IRON_CHESTPLATE:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
            case DIAMOND_HELMET:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_LEGGINGS:
            case DIAMOND_BOOTS:
            case GOLDEN_HELMET:
            case GOLDEN_CHESTPLATE:
            case GOLDEN_LEGGINGS:
            case GOLDEN_BOOTS:
            case SHIELD:
                return EQUIPMENT;
            default:
                return REGULAR;
        }
    }
}
