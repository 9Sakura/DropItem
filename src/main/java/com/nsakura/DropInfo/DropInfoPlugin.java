package com.nsakura.DropInfo;

import com.nsakura.DropInfo.Utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.Map;

public class DropInfoPlugin extends JavaPlugin {
    public Map<String, String> localizationMap;

    @Override
    public void onLoad() {
        super.onLoad();
        // 加载语言文件
        Utils.getInstance().setPlugin(this);
        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdir()) {
                Utils.getInstance().handleException(new IOException("Failed to execute mkdir"), "无法创建数据目录");
            }
        }
        // TODO: 从网络下载语言文件
        File localization = new File(getDataFolder(), "zh_cn_pctmp.json");
        if (!localization.exists()) {
            InputStream json = (getClass().getResourceAsStream("/zh_cn_pctmp.json"));
            try {
                Files.copy(json, localization.getAbsoluteFile().toPath());
            } catch (IOException e) {
                Utils.getInstance().handleException(e, "无法写入语言文件");
            }
        }
        // 读取语言文件
        try {
            FileReader reader = new FileReader(localization);
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, String>>() {}.getType();
            localizationMap = gson.fromJson(reader, mapType);
        } catch (FileNotFoundException e) {
            Utils.getInstance().handleException(e, "无法读取语言文件");
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(new DropInfoListener(this), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();

    }
}
