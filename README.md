# Minecraft-UtilityLib
![alt text](https://github.com/Wonkglorg/Minecraft-UtilityLib/blob/master/Logo.png?raw=true)
## Index

* [Introduction](#introduction)
* [Requirements](#requirements)
* [Installation](#installation)
* [Overview](#overview)
* [Guides](#guide)
* [Credits](#credits)

## <a name="introduction"></a>Introduction

This library serves as a helpful tool for plugin developers by eliminating the tedium of setting up each plugin's
required functionality every time. Instead, letting them concentrate on the functionality of the plugin itself.

## <a name="requirements"></a>Requirements

* Spigot or Paper
* Minecraft 1.16.* and above
* JAVA 8 or above

## <a name="installation"></a>Installation

    ```yaml
		<dependency>
			<groupId>com.github.Wonkglorg</groupId>
			<artifactId>Minecraft-UtilityLib</artifactId>
			<version>version</version>
		</dependency> 
    ```

## <a name="overview"></a>Overview

* Config
    * Generation and manipulation of configs written in YML or JSON format.


* Message components
    * Convert strings to the new adventure API components, using either hex values or
      Minecraft-supported colors.
    * Methods to create gradients between any amount of colors on a given string
    * Getting a color between any amount of colors based on a value entered. For example used for damage numbers that
      change color the higher they get


* Managers
    * Contains multiple managers that handle all the necessary steps to define commands, config files, events, and
      other things. These managers can be called using a global manager to modify their contents or retrieve specific
      contents.

* GUI
    * Supports creation of inventory with various functionality.


* Builders
    * A big selection of builders to make creation of various things like custom crafting recipes easier.


* Database
    * Methods to create connections checking file existence and more.


* Utility
    * Various utility methods for items, entities, conversions.

  ## <a name="guide"></a>Guides

    * [UtilityPlugin](#utilityplugin)
  ### <a name="utilityplugin"></a>UtilityPlugin
  To use the PluginManager use the UtilityPlugin class to extend your main class instead of JavaPlugin
    ```java
    public class Main extends UtilityPlugin
    {
    //code
    }     
    ```

  To access the manager use the manager variable or the static getManager() method.

## <a name="credits"></a>Credits

This plugin is being developed by [Wonkglorg](https://gitlab.com/u/Wonkglorg). Some methods were adapted
from existing ones that may be found either on [Bukkit](http://bukkit.org/forums/)
or [Spigot](https://www.spigotmc.org/forums/) forum.

please do not hesitate to contact me if you recognize your code snippets. I am happy to add proper credits or
potentially rework the code if you do not agree with its usage.
