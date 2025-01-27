package com.github.it.tangTaMeokGi

import com.github.it.tangTaMeokGi.area.BaseArea
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TangTaMeokGi : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
