package com.github.it.tangTaMeokGi

import com.github.it.tangTaMeokGi.game.area.Area
import com.github.it.tangTaMeokGi.game.GameManager
import com.github.it.tangTaMeokGi.game.team.Team
import kotlinx.coroutines.*
import kotlinx.coroutines.Runnable
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin



class TangTaMeokGi : JavaPlugin() {
    val pluginScope = CoroutineScope(Dispatchers.Default + Job())

    val gameManager = GameManager(this)

//    lateinit var testPlayer: Player
//    lateinit var testArea: Area


    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))

        gameManager.init(
            mapSize = 16, areaSize = 16, gameTimeMin = 1
        )

        pluginScope.launch {
            gameManager.mapGenerate()
        }

//        testArea = gameManager.areaManager!!.getArea(1, 1)!!
//
//        gameManager.teamManager!!.addTeam(
//            Team("test", "test", Color.RED)
//        )
//
//        testPlayer = Bukkit.getServer().getPlayer("ityeri")!!
//        gameManager.teamManager!!.getTeam("test")!!.addPlayer(testPlayer)
//
//        testArea.enable()
    }

    override fun onDisable() {
    }
}