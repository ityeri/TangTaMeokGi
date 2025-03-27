package com.github.it.tangTaMeokGi.area

import com.github.it.tangTaMeokGi.SubWorldUtils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.plugin.java.JavaPlugin
import kotlin.random.Random

class AreaManager(
    val world: World,
    val plugin: JavaPlugin,
    val mapWidth: Int,
    val mapDepth: Int,
    val areaWidth: Int,
    val areaDepth: Int
) {

    private val areaMap: MutableList<MutableList<Area>> = MutableList(mapDepth) { mutableListOf() }

    val generalGroundItem = Material.IRON_AXE
    val specialGroundItem = Material.NETHERITE_AXE



    fun generate() {

        for (z in 0 until  mapDepth) {
            val currentLine = areaMap[z]
            for (x in 0 until  mapWidth) {
                currentLine.add(Area(this, world, plugin,
                    x, z, areaDepth, areaDepth
                ))
            }
        }


        val totalMapWidth: Int = mapWidth * areaWidth
        val totalMapDepth: Int = mapDepth * areaDepth

        world.worldBorder.setCenter(totalMapWidth / 2.0, totalMapDepth / 2.0)

        val worldBorderSize: Double

        if (totalMapDepth < totalMapWidth) worldBorderSize = totalMapWidth.toDouble()
        else worldBorderSize = totalMapDepth.toDouble()

        world.worldBorder.size = worldBorderSize
        world.worldBorder.damageBuffer = 0.0
        world.worldBorder.warningDistance = 0
        world.worldBorder.warningTime = 0



        Bukkit.getLogger()
            .info("청크 기준 크기: $mapWidth * $mapDepth | 총 크기: $totalMapWidth * $totalMapDepth 영역의 생성을 시작합니다.")



        val totalProgress: Double = (mapWidth * mapDepth).toDouble()
        var currentProgress: Int = 0

        var x = 0
        var z = 0

        var taskId = -1

        taskId = Bukkit.getScheduler().runTaskTimer(plugin, Runnable {
            val world: World
            if (Random.nextFloat() < 0.7) {
                world = SubWorldUtils.getSubOverWorld()
            } else {
                world = SubWorldUtils.getSubNetherWorld()
            }
            getArea(x, z)!!.regenerateFrom(
                world, Random.nextInt(-100000, 100000), Random.nextInt(-100000, 100000)
            )

            currentProgress += 1

            Bukkit.getLogger()
                .info("${((currentProgress / totalProgress) * 100).toInt()} % | [$x, $z] / [$mapWidth, $mapDepth] 영역 완료.")

            x += 1

            if (mapWidth <= x) {
                x = 0; z += 1
            }
            if (mapDepth <= z) {
                Bukkit.getLogger()
                    .info("청크 기준 크기: $mapWidth * $mapDepth | 총 크기: $totalMapWidth * $totalMapDepth 영역의 생성이 완료되었습니다.")

                Bukkit.getScheduler().cancelTask(taskId)
            }

        }, 0L, 1L).taskId



    }

    fun update() {
        for (z in 0 until  mapDepth) {
            for (x in 0 until mapWidth) {
                getArea(x, z)!!.update()
            }
        }
    }



    fun getArea(x: Int, z: Int): Area? {
        try {
            return areaMap[z][x]
        } catch (e: IndexOutOfBoundsException) {
            return null
        }
    }



    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        event.itemDrop.itemStack.type
    }
}
