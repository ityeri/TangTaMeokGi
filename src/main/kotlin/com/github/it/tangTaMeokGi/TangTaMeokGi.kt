package com.github.it.tangTaMeokGi

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaState.GeneralAreaState
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

    lateinit var testPlayer: Player
    lateinit var testArea: Area
    lateinit var testTeam: Team


    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))

        game.init(
            mapSize = 16, areaSize = 16,
            totalGameTimeMin = 60,
            warTimeSec = 60
        )

        testArea = game.areaManager!!.getArea(1, 1)!!

        testTeam = Team("test", "test", Color.RED)
        game.teamManager!!.addTeam(testTeam)

        testPlayer = Bukkit.getServer().getPlayer("ityeri")!!
        game.teamManager!!.getTeam("test")!!.addPlayer(testPlayer)

        testArea.state = GeneralAreaState(
            testArea, testTeam
        )
        testArea.enable()

        userInterface.enable()
    }

    override fun onDisable() {
    }
}