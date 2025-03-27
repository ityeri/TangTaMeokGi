package com.github.it.tangTaMeokGi.game

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.AreaManager
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class GameManager(val plugin: JavaPlugin) {
    private val isGameRunning = false
        get() = field

    private var mapWidth: Int? = null
    private var mapDepth: Int? = null

    private var areaWidth: Int? = null
    private var areaDepth: Int? = null

    private var areaManager: AreaManager? = null

    private val teamStore: MutableMap<String, Team> = mutableMapOf()

    private var gameTime: Int? = null

    private var gameStartTime: Int? = null
    private var gameEndTime: Int? = null
    // TODO

    fun reset() {
        mapWidth = null
        mapDepth = null

        areaWidth = null
        areaDepth = null

        areaManager = null
        teamStore.clear()

        gameTime = null

        gameStartTime = null
        gameEndTime = null
    }

    fun setMapSize(width: Int, depth: Int) {
        this.mapWidth = width
        this.mapDepth = depth
    }
    fun setAreaSize(width: Int, depth: Int) {
        this.areaWidth = width
        this.areaDepth = depth
    }

    fun checkSettingAvailable(): Boolean {
        mapWidth ?: run { return false }
        mapDepth ?: run { return false }

        areaWidth ?: run { return false }
        areaDepth ?: run { return false }

        gameTime ?: run { return false }

        return true
    }

    fun areaGenerate() {

        if (!checkSettingAvailable()) {
            throw IllegalStateException("필수 설정이 지정되지 않았습니다")
        }

        areaManager = AreaManager(
            world = Bukkit.getWorld("world")!!,
            plugin = plugin,

            mapWidth = mapWidth!!,
            mapDepth = mapDepth!!,

            areaWidth = areaWidth!!,
            areaDepth = areaDepth!!
        )

        areaManager!!.generate()
    }
}