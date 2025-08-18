package com.github.ityeri.tangTaMeokGi

import com.github.ityeri.tangTaMeokGi.core.Game
import com.github.ityeri.tangTaMeokGi.core.event.GameEventListener
import com.github.ityeri.tangTaMeokGi.userInterface.UserInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
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