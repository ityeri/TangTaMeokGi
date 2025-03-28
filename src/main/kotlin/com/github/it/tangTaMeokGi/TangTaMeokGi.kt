package com.github.it.tangTaMeokGi

import com.github.it.tangTaMeokGi.area.Area
import com.github.it.tangTaMeokGi.area.AreaManager
import com.github.it.tangTaMeokGi.area.areaState.PublicAreaState
import com.github.it.tangTaMeokGi.game.GameManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin

class TangTaMeokGi : JavaPlugin(), Listener {
    val gameManager = GameManager(this)

    val areaManager: AreaManager = AreaManager(gameManager, 16, 1)
    val testArea: Area

    init {
        areaManager.generate()
        testArea = areaManager.getArea(1, 1)!!
        testArea.state = PublicAreaState(testArea, false)
    }

    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))
//        areaManager.mapGenerate()
        testArea.enable()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}