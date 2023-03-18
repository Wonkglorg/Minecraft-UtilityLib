# Minecraft-UtilityLib

Utility Lib for my plugins

## Index

* [Introduction](#introduction)
* [Requirements](#requirements)
* [Overview](#overview)
* [Guides](#guide)
* [Other Info](#otherInfo)

## <a name="introduction"></a>Introduction

This library serves as a helpful tool for plugin developers by eliminating the tedium of setting up each plugin's
required functionality every time. Instead, letting them concentrate on the functionality of the plugin itself.

## <a name="requirements"></a>Requirements

* Spigot or Paper
* Minecraft 1.8.* and above
* JAVA 8 or above

## <a name="overview"></a>Overview

* Config
    * Supports generation and manipulation of configs written in YML or JSON format.


* Message components
    * Convert strings formatted into the new adventure API components, using either hex values or
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

## <a name="otherInfo"></a>Other Information

Please note that some of the code used in this project consists of snippets that I have collected over the years and
modified. If you recognize that you are the original owner of any of these code snippets, please do not hesitate to
contact me. I am happy to add proper credits or potentially rework the code if you do not agree with its usage.