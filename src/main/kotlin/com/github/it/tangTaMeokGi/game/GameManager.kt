package com.github.it.tangTaMeokGi.game

import com.github.it.tangTaMeokGi.game.team.Team
import com.github.it.tangTaMeokGi.area.AreaManager
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin

class GameManager(val plugin: JavaPlugin) {
    private var isGameRunning = false

    val world: World = Bukkit.getWorld("world")!!

    var mapSize: Int? = null
    var areaSize: Int? = null

    private var areaManager: AreaManager? = null

    private val teamStore: MutableMap<String, Team> = mutableMapOf()

    private var gameTime: Int? = null

    private var gameStartTime: Int? = null
    private var gameEndTime: Int? = null
    // TODO

    fun reset() {
        mapSize = null
        areaSize = null

        areaManager.let {
            areaManager!!.disableAll()
        }

        areaManager = null

        teamStore.clear()

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