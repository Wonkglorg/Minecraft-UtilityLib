package com.wonkglorg.utilitylib.manager.managers;

import com.wonkglorg.utilitylib.base.logger.Logger;
import com.wonkglorg.utilitylib.manager.config.Config;
import com.wonkglorg.utilitylib.manager.config.ConfigYML;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

@SuppressWarnings("unused")
public final class ConfigManager implements Manager {
    private final JavaPlugin plugin;
    private boolean isStarted;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private final Map<String, Config> configMap = new HashMap<>();

    /**
     * returns true if config added
     *
     * @param config
     * @return
     */
    public synchronized void add(@NotNull Config config) {
        configMap.putIfAbsent(config.path(), config);
        config.silentLoad();
    }

    /**
     * returns true if set changed
     *
     * @param configs
     * @return
     */
    public synchronized void add(@NotNull Config... configs) {
        for (Config config : configs) {
            configMap.putIfAbsent(config.path(), config);
        }
        Arrays.stream(configs).toList().forEach(Config::silentLoad);
    }

    public synchronized void add(@NotNull Collection<Config> configs) {
        for (Config config : configs) {
            configMap.putIfAbsent(config.path(), config);
        }
        configs.forEach(Config::silentLoad);
    }

    public synchronized void load() {
        configMap.values().forEach(Config::load);
    }

    public synchronized void silentLoad() {
        configMap.values().forEach(Config::silentLoad);
    }

    public synchronized void save() {
        configMap.values().forEach(Config::save);
    }

    public synchronized void silentSave() {
        configMap.values().forEach(Config::silentSave);
    }

    /**
     * Can cause issues if you have multiple configs with the same name not recommended
     * Slower than getting config by path
     *
     * @param name
     * @return
     */
    public synchronized Config getConfigByName(String name) {
        for (Config config : configMap.values()) {
            if (config.name().equalsIgnoreCase(name)) {
                return config;
            }
        }
        return null;
    }

    /**
     * Gets a congig by its path checks for both entered path and data folder path of the plugin
     * automatixally prefixes the data folder path if not already present
     *
     * @param path
     * @return
     */
    public synchronized Config getConfig(String path) {

        path = path.startsWith(plugin.getDataFolder().toString()) ? path : Path.of(plugin.getDataFolder().toString(), path).toString();
        return configMap.get(path);

    }

    @Override
    public void onShutdown() {
        if (!configMap.isEmpty()) {
            silentSave();
            Logger.log(plugin, "Saved " + configMap.size() + " configs!");
        }
    }

    @Override
    public void onStartup() {
        if (isStarted) {
            return;
        }
        isStarted = true;
        if (!configMap.isEmpty()) {
            Logger.log(plugin, "Loaded " + configMap.size() + " configs!");
        }
    }

    public synchronized Map<String, Config> addAllConfigsFromPath(String... paths) {
        if (paths.length == 0) {
            return null;
        }
        String first = paths[0];
        String[] more = Arrays.copyOfRange(paths, 1, paths.length);
        Path path = Path.of(first, more);
        return addAllConfigsFromPath(path);
    }

    public synchronized Map<String, Config> addAllConfigsFromPath(Path path) {
        File[] files = Path.of(plugin.getDataFolder().getPath(), path.toString()).toFile().listFiles();
        Map<String, Config> tempConfigs = new HashMap<>();
        if (files == null) {
            return null;
        }
        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }
            if (!file.getName().endsWith(".yml")) {
                continue;
            }
            Config config = new ConfigYML(plugin, file.toPath());
            add(config);
            tempConfigs.put(file.getName(), config);
        }

        return tempConfigs;
    }

    public Collection<Config> getConfigMap() {
        return configMap.values();
    }
}