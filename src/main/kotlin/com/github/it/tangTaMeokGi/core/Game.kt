package com.github.it.tangTaMeokGi.core

import com.github.it.tangTaMeokGi.core.area.AreaManager
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.EffectAreaData.AreaPotionEffect
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.PublicAreaData
import com.github.it.tangTaMeokGi.core.event.GameEndEvent
import com.github.it.tangTaMeokGi.core.event.GameEventDispatcher
import com.github.it.tangTaMeokGi.core.event.GameStartEvent
import com.github.it.tangTaMeokGi.core.team.TeamManager
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin
import kotlin.random.Random

class Game(val plugin: JavaPlugin) {
    var isGameRunning = false
    var isInitialized = false

    var world: World? = null

    var areaManager: AreaManager? = null
    var teamManager: TeamManager? = null
    val eventDispatcher = GameEventDispatcher()

    var setting: GameSetting? = null

    var gameTimeLeft: Double = -1.0

    var updateTaskId: Int? = null
    var lastUpdateTime: Double = -1.0
    // TODO



    fun reset() {
        isInitialized = false
        isGameRunning = false

        areaManager.let {
            areaManager!!.disable()
        }

        areaManager = null
        teamManager = null

        setting = null

        gameTimeLeft = -1.0
        lastUpdateTime = -1.0
    }

    fun init(
        world: World,
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
        this.world = world

        initArea()

        isInitialized = true
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

        areaManager!!.baseGenerate()
    }

    fun areaTypeGenerate(effectAreaProbability: Double) {
        for (area in areaManager!!.getAllArea()) {
            if (Random.nextFloat() < effectAreaProbability) {
                area.data = PublicAreaData(
                    area, true, AreaPotionEffect()
                )
            }
            else {
                area.data = PublicAreaData(
                    area, false
                )
            }
        }
    }

    suspend fun mapGenerate() {
        setting ?: {
            throw IllegalStateException("설정이 지정되지 않았습니다")
        }

        areaManager!!.mapGenerate()
    }



    fun start() {
        if (isGameRunning || !isInitialized) { throw IllegalStateException() }

        isGameRunning = true
        areaManager!!.enable()

        gameTimeLeft = setting!!.totalGameTime.toDouble()
        lastUpdateTime = System.currentTimeMillis() / 1000.0

        updateTaskId = Bukkit.getScheduler().runTaskTimer(plugin,
            Runnable { update() }, 1L, 1L).taskId

        eventDispatcher.callEvent(GameStartEvent())
    }

    fun pause() {
        isGameRunning = false
        areaManager!!.disable()
    }
    fun unpause() {
        isGameRunning = true
        areaManager!!.enable()
    }

    fun end() {
        if (!isGameRunning || !isInitialized) { throw IllegalStateException() }

        isGameRunning = false
        areaManager!!.disable()

        Bukkit.getScheduler().cancelTask(updateTaskId!!)

        eventDispatcher.callEvent(GameEndEvent())
    }



    fun update() {
        if (!isGameRunning) { return }

        val currentTime = System.currentTimeMillis() / 1000.0
        val timeDelta = currentTime - lastUpdateTime

        gameTimeLeft -= timeDelta

        if (gameTimeLeft <= 0) {
            end()
        }

        lastUpdateTime = currentTime
    }
}