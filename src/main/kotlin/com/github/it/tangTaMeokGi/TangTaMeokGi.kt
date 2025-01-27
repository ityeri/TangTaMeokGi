package com.github.it.tangTaMeokGi

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.plugin.java.JavaPlugin

class TangTaMeokGi : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))
        println(Bukkit.getWorld("world")!!.getBlockAt(0, -100, 0).type)

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
