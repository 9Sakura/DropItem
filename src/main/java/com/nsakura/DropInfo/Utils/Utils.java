package com.nsakura.DropInfo.Utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Utils {
    // Singleton
    private static Utils INSTANCE;
    private Utils() {}
    public static Utils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Utils();
        }
        return INSTANCE;
    }

    private final Logger logger = Logger.getLogger("Minecraft");
    private JavaPlugin plugin;

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void handleException(Exception e, String reason) {
        logger.severe(reason + "，已禁用插件。");
        logger.severe("请将下面信息发给插件姬。");
        logger.severe("------ 信息头部 ------");
        logger.severe(e.getLocalizedMessage());
        logger.severe("------ 信息尾部 ------");
        Bukkit.getServer().getPluginManager().disablePlugin(plugin);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void warning(String msg) {
        logger.warning(msg);
    }

    public void error(String msg) {
        logger.severe(msg);
    }
}
