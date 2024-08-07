package com.wonkglorg.utilitylib.manager;

import com.wonkglorg.utilitylib.manager.managers.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class UtilityPlugin extends JavaPlugin {
    protected static PluginManager manager;

    public UtilityPlugin() {
        manager = new PluginManager(this);
    }

    /**
     * Executes on plugin startup
     */
    public abstract void pluginStartup();

    /**
     * Executes on plugin shutdown
     */
    public abstract void pluginShutdown();
	
	
	/*
	MAKE PLUGINMANAGER A MAP WITH PLUGINS AS THE KEY, ALLOWS FOR 1 JAR TO HANDLE ALL THE DIFFERENT PLUGINS WHO MIGHT ACCESS IT WHEN IT IS A PROVIDED DEPENDENCY
	REMEMBER FOR LATER ALSO IMPROVE API
	 */

    @Override
    public void onEnable() {
        pluginStartup();
        manager.onStartup();
        getResource("config.yml");
    }


    @Override
    public void onDisable() {
        pluginShutdown();
        manager.onShutdown();
    }

    public static PluginManager manager() {
        return manager;
    }

    public PluginManager getManager() {
        return manager;
    }

    /**
     * Checks if a specific plugin dependency with a given name exists
     *
     * @param pluginName plugin name
     * @return
     */
    public boolean dependencyExists(String pluginName) {
        String corePlug = null;
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
        for (Plugin testPlug : plugins) {

            if (testPlug.getName().contains(pluginName)) {
                corePlug = testPlug.getName();
                break;
            }
        }
        if (corePlug == null) {
            return false;
        }
        Plugin corePlugin = Bukkit.getPluginManager().getPlugin(corePlug);
        return corePlugin != null;
    }

}