package com.github.it.tangTaMeokGi

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.block.BlockState
import org.bukkit.plugin.java.JavaPlugin

class TangTaMeokGi : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
