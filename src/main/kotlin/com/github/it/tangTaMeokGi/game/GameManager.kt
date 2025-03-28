package com.github.it.tangTaMeokGi.game

import com.github.it.tangTaMeokGi.game.team.Team
import com.github.it.tangTaMeokGi.area.AreaManager
import com.github.it.tangTaMeokGi.game.team.TeamManager
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin

class GameManager(val plugin: JavaPlugin) {
    var isGameRunning = false

    val world: World = Bukkit.getWorld("world")!!

    var mapSize: Int? = null
    var areaSize: Int? = null

    var areaManager: AreaManager? = null

    var teamManager: TeamManager? = null

    var gameTime: Int? = null

    var gameStartTime: Int? = null
    var gameEndTime: Int? = null
    // TODO

    fun reset() {
        mapSize = null
        areaSize = null

        areaManager.let {
            areaManager!!.disableAll()
        }

        areaManager = null
        teamManager = null

        gameTime = null

        gameStartTime = null
        gameEndTime = null
    }

    fun checkSettingAvailable(): Boolean {
        mapSize ?: run { return false }

        areaSize ?: run { return false }

        gameTime ?: run { return false }

        return true
    }

    fun areaGenerate() {

        if (!checkSettingAvailable()) {
            throw IllegalStateException("필수 설정이 지정되지 않았습니다")
        }

        areaManager = AreaManager(
            gameManager = this,

            mapSize = mapSize!!,
            areaSize = areaSize!!
        )

        areaManager!!.generate()
        areaManager!!.setWorldBorder()
    }

    fun start() {
        isGameRunning = true
        areaManager!!.enableAll()
    }
}