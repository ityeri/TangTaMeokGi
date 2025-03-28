package com.github.it.tangTaMeokGi

import com.github.it.tangTaMeokGi.game.area.Area
import com.github.it.tangTaMeokGi.game.GameManager
import com.github.it.tangTaMeokGi.game.team.Team
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class TangTaMeokGi : JavaPlugin(), Listener {
    val gameManager = GameManager(this)

    lateinit var testPlayer: Player
    lateinit var testArea: Area


    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))

        gameManager.init(
            mapSize = 4, areaSize = 4, gameTimeMin = 1
        )

        testArea = gameManager.areaManager!!.getArea(1, 1)!!

        gameManager.teamManager!!.addTeam(
            Team("test", "test", Color.RED)
        )

        gameManager.teamManager!!.getTeam("test")!!.addPlayer(testPlayer)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}