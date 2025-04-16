package com.github.it.tangTaMeokGi

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.event.*
import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.userInterface.UserInterface
import kotlinx.coroutines.*
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import java.awt.Color
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin



class TangTaMeokGi : JavaPlugin(), GameEventListener {
    val pluginScope = CoroutineScope(Dispatchers.Default + Job())

    val game = Game(this)
    val userInterface = UserInterface(game, pluginScope)


    override fun onEnable() {
        Bukkit.getServer().sendMessage(Component.text("탕타묵기"))

        game.init(
            world = Bukkit.getWorld("world")!!,
            mapSize = 16, areaSize = 16,
            totalGameTimeMin = 1,
            warTimeSec = 5
        )

        game.areaTypeGenerate(0.3)

        userInterface.enable()

        game.start()

    }

    override fun onDisable() {
        userInterface.disable()
    }
}