package com.github.it.tangTaMeokGi

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.team.Team
import kotlinx.coroutines.*
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin



class TangTaMeokGi : JavaPlugin() {
    val pluginScope = CoroutineScope(Dispatchers.Default + Job())

    val game = Game(this)

    lateinit var testPlayer: Player
    lateinit var testArea: Area


    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))

        game.init(
            mapSize = 16, areaSize = 16,
            totalGameTimeMin = 60,
            warTimeSec = 60
        )

//        pluginScope.launch {
//            game.mapGenerate()
//        }

        testArea = game.areaManager!!.getArea(1, 1)!!
//        testArea.type = AreaType.EMPTY_AREA

        game.teamManager!!.addTeam(
            Team("test", "test", Color.RED)
        )

        testPlayer = Bukkit.getServer().getPlayer("ityeri")!!
        game.teamManager!!.getTeam("test")!!.addPlayer(testPlayer)

        testArea.enable()
        println(testArea.type)
    }

    override fun onDisable() {
    }
}