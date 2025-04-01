package com.github.it.tangTaMeokGi

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.GeneralAreaData
import com.github.it.tangTaMeokGi.core.event.*
import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.userInterface.UserInterface
import kotlinx.coroutines.*
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin



class TangTaMeokGi : JavaPlugin(), GameEventListener {
    val pluginScope = CoroutineScope(Dispatchers.Default + Job())

    val game = Game(this)
    val userInterface = UserInterface(game)

    lateinit var testPlayer1: Player
    lateinit var testPlayer2: Player
    lateinit var testArea: Area
    lateinit var testTeam1: Team
    lateinit var testTeam2: Team


    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))

        game.init(
            mapSize = 16, areaSize = 16,
            totalGameTimeMin = 60,
            warTimeSec = 10
        )

        testArea = game.areaManager!!.getArea(1, 1)!!

        testTeam1 = Team("test1", "test1", Color.RED)
        testTeam2 = Team("test2", "test2", Color.BLUE)

        game.teamManager!!.addTeam(testTeam1)
        game.teamManager!!.addTeam(testTeam2)

        testPlayer1 = Bukkit.getServer().getPlayer("ityeri")!!
        testPlayer2 = Bukkit.getServer().getPlayer("ritiey")!!

        game.teamManager!!.getTeam("test1")!!.addPlayer(testPlayer1)
        game.teamManager!!.getTeam("test2")!!.addPlayer(testPlayer2)

        testArea.data = GeneralAreaData(
            testArea, testTeam2
        )
        testArea.enable()

        userInterface.enable()
    }

    override fun onDisable() {
    }
}