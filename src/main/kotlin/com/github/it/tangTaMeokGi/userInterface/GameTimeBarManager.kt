package com.github.it.tangTaMeokGi.userInterface

import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.event.GameStartEvent
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle

class GameTimeBarManager(game: Game) {

    val gameTimeBarNamespacedKey = NamespacedKey(game.plugin, "game_time_bar")
    val gameTimeBar = Bukkit.createBossBar(
        gameTimeBarNamespacedKey,
        "남은 겜시간", BarColor.YELLOW, BarStyle.SOLID
    )

    fun onGameStart(event: GameStartEvent) {
        Bukkit.getServer().onlinePlayers.forEach { player ->
            gameTimeBar.addPlayer(player)
        }
    }
}