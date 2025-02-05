package com.github.it.tangTaMeokGi

import com.github.it.tangTaMeokGi.area.Area
import com.github.it.tangTaMeokGi.area.AreaManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.block.BlockState
import org.bukkit.plugin.java.JavaPlugin

class TangTaMeokGi : JavaPlugin() {
    val areaManager: AreaManager = AreaManager(Bukkit.getWorld("world")!!,
        8, 8, 8, 8)

    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))
        areaManager.generate()

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
