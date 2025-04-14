package com.github.it.tangTaMeokGi.userInterface

import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.event.GameEndEvent
import com.github.it.tangTaMeokGi.core.event.GameEventHandler
import com.github.it.tangTaMeokGi.core.event.GameEventListener
import com.github.it.tangTaMeokGi.core.event.GameStartEvent
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class GameTimeBarManager(val game: Game) : Listener, GameEventListener {

    val gameTimeBarNamespacedKey = NamespacedKey(game.plugin, "game_time_bar")
    val gameTimeBar = Bukkit.createBossBar(
        gameTimeBarNamespacedKey,
        "남은 겜시간", BarColor.YELLOW, BarStyle.SOLID
    )

    fun enable() {
        game.eventDispatcher.register(this)
    }

    fun disable() {
        Bukkit.removeBossBar(gameTimeBarNamespacedKey)
    }

    @GameEventHandler
    fun onGameStart(event: GameStartEvent) {
        gameTimeBar.isVisible = true

        Bukkit.getServer().onlinePlayers.forEach { player ->
            gameTimeBar.addPlayer(player)
        }
    }

    @GameEventHandler
    fun onGameEnd(event: GameEndEvent) {
        gameTimeBar.isVisible = false
    }

    fun update() {
        val gameTimeLeftPercent = game.gameTimeLeft / game.setting!!.totalGameTime
        gameTimeBar.progress = gameTimeLeftPercent.coerceIn(0.0, 1.0)
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        gameTimeBar.addPlayer(event.player)
    }
}