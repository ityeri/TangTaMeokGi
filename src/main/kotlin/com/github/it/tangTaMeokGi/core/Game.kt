package com.github.it.tangTaMeokGi.core

import com.github.it.tangTaMeokGi.core.area.AreaManager
import com.github.it.tangTaMeokGi.core.event.GameEventDispatcher
import com.github.it.tangTaMeokGi.core.team.TeamManager
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin

class Game(val plugin: JavaPlugin) {
    var isGameRunning = false

    val world: World = Bukkit.getWorld("world")!!

    var areaManager: AreaManager? = null
    var teamManager: TeamManager? = null
    val eventDispatcher = GameEventDispatcher()

    var setting: GameSetting? = null

    var gameStartTime: Int? = null
    var gameEndTime: Int? = null
    // TODO



    fun reset() {

        areaManager.let {
            areaManager!!.disableAll()
        }

        areaManager = null
        teamManager = null

        setting = null

        gameStartTime = null
        gameEndTime = null
    }

    fun init(
        mapSize: Int,
        areaSize: Int,
        totalGameTimeMin: Int,
        warTimeSec: Int
    ) {
        setting = GameSetting(
            mapSize = mapSize,
            areaSize = areaSize,
            totalGameTime = totalGameTimeMin * 60,
            warTime = warTimeSec
        )

        teamManager = TeamManager()

        initArea()
    }



    fun initArea() {

        setting ?: {
            throw IllegalStateException("설정이 지정되지 않았습니다")
        }

        areaManager = AreaManager(
            game = this,

            mapSize = setting!!.mapSize,
            areaSize = setting!!.areaSize
        )

        areaManager!!.generate()
        areaManager!!.setWorldBorder()
    }

    suspend fun mapGenerate() {
        setting ?: {
            throw IllegalStateException("설정이 지정되지 않았습니다")
        }

        areaManager!!.mapGenerate()
    }



    fun start() {
        isGameRunning = true
        areaManager!!.enableAll()
    }
}