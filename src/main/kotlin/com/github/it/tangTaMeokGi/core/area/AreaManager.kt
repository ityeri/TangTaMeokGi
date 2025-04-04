package com.github.it.tangTaMeokGi.core.area

import com.github.it.tangTaMeokGi.core.BukkitSyncTaskBatch
import com.github.it.tangTaMeokGi.core.SubWorldUtils
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.area.areaData.warAreaData.BaseWarAreaData
import com.github.it.tangTaMeokGi.core.event.AreaOccupationEvent
import com.github.it.tangTaMeokGi.core.event.GameEventHandler
import com.github.it.tangTaMeokGi.core.event.GameEventListener
import com.github.it.tangTaMeokGi.core.team.Team
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import kotlin.random.Random
import kotlinx.coroutines.*
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Entity

class AreaManager(
    val game: Game,
    val mapSize: Int,
    val areaSize: Int
): GameEventListener {

    val plugin = game.plugin
    val world = game.world

    val areaMap: MutableList<MutableList<Area>> = MutableList(mapSize) { mutableListOf() }
    
    fun isGroundItem(item: ItemStack): Boolean {
        // TODO 이 매서드는 GameManager 로 옮기거나 암튼 더 합리적인 위치로 이동 ㄱ
        return item.type == Material.IRON_AXE
    }

    fun enableAll() {
        for (area in getAllArea()) {
            area.enable()
        }
    }
    fun disableAll() {
        for (area in getAllArea()) {
            area.disable()
        }
    }

    fun setWorldBorder() {
        val totalMapSize: Int = mapSize * areaSize

        world.worldBorder.setCenter(totalMapSize / 2.0, totalMapSize / 2.0)

        world.worldBorder.size = totalMapSize.toDouble()
        world.worldBorder.damageBuffer = 0.0
        world.worldBorder.warningDistance = 0
        world.worldBorder.warningTime = 0
    }

    fun baseGenerate() {

        for (z in 0 until  mapSize) {
            val currentLine = areaMap[z]
            for (x in 0 until  mapSize) {
                currentLine.add(Area(this, x, z, areaSize))
            }
        }
    }

    fun generateRandom(effectAreaProbability: Float) {
        for (area in getAllArea()) {
            if (Random.nextFloat() < effectAreaProbability) {
                area.type = AreaType.PUBLIC_AREA
            }
        }
    }

    suspend fun mapGenerate(batchCount: Int = 4) {

        Bukkit.getScheduler().callSyncMethod(plugin) {
            Bukkit.getServer().sendMessage(Component.text(
                "생성 시작"
            ))
        }

        val scope = CoroutineScope(Dispatchers.Default + Job())

        val batch = BukkitSyncTaskBatch(plugin, 50, 512)
        batch.start()

        val jobs: MutableList<Job> = mutableListOf()

        var worldUnloadTimer = 0
        val worldUnloadInterval = 10

        Bukkit.getScheduler().callSyncMethod(plugin) {
            setWorldBorder()
        }

        for (z in 0 until  mapSize) {
            for (x in 0 until mapSize) {
                val world: World

                if (Random.nextFloat() < 0.7) {
                    world = Bukkit.getScheduler().callSyncMethod(plugin) {
                        SubWorldUtils.getSubOverWorld()
                    }.get()
                } else {
                    world = Bukkit.getScheduler().callSyncMethod(plugin) {
                        SubWorldUtils.getSubNetherWorld()
                    }.get()
                }


                val area = getArea(x, z)!!

                batch.open()
                val job = scope.launch {
                    area.batchGenerateFrom(batch, world,
                        Random.nextInt(-100000, 100000),
                        Random.nextInt(-100000, 100000)
                    )
                }


                job.join()

                batch.close()
                batch.join()

                Bukkit.getScheduler().callSyncMethod(plugin) {
                    Bukkit.getServer().sendMessage(Component.text(
                        "[$x, $z] 영역 완료."
                    ))
                }

                jobs.add(job)


                worldUnloadTimer += 1

                if (worldUnloadInterval <= worldUnloadTimer) {
                    worldUnloadTimer = 0
                    Bukkit.getScheduler().callSyncMethod(plugin)  {
                        Bukkit.unloadWorld(world, true)
                    }
                }
            }
        }

        batch.stop()

        Bukkit.getScheduler().callSyncMethod(plugin) {
            Bukkit.getServer().sendMessage(Component.text(
                "전체 완료"
            ))
        }

    }


    fun update() {
        for (z in 0 until  mapSize) {
            for (x in 0 until mapSize) {
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
    fun getArea(entity: Entity): Area? {
        val x = (entity.x / areaSize).toInt()
        val z = (entity.z / areaSize).toInt()

        return getArea(x, z)
    }

    fun getAllArea(): List<Area> {
        val areas: MutableList<Area> = mutableListOf()

        for (line in areaMap) {
            areas.addAll(line)
        }

        return areas.toList()
    }



    @GameEventHandler
    fun onAreaOccupation(areaOccupationEvent: AreaOccupationEvent) {
        val team = areaOccupationEvent.winningTeam
        val checkedAreas: MutableSet<Area> = mutableSetOf()
        val adjacentOffsets: List<List<Int>> = listOf(
            listOf(-1, 0), listOf(1, 0), listOf(0, -1), listOf(0, 1)
        )

        for (z in 0 until mapSize) {
            for (x in 0 until mapSize) {
                val seedArea = getArea(x, z)!!

                if (seedArea in checkedAreas) { continue }

                val currentCheckingAreas = mutableSetOf(seedArea)

                while (true) {
                    var isClosed = true

                    for (currentCheckingArea in currentCheckingAreas) {
                        for (adjacentOffset in adjacentOffsets) {
                            // TODO
                        }

                    }
                }
            }
        }
    }

    fun checkCloseSpaceFrom(x: Int, z: Int, team: Team): Pair<Boolean, Set<Area>> {
        // TODOTODOTODOTODOTODOTODOTODOTODOTODOTODO
        val seedArea = getArea(x, z)!!
        val checkedAreas = mutableSetOf<Area>()
        val adjacentOffsets: List<List<Int>> = listOf(
            listOf(-1, 0), listOf(1, 0), listOf(0, -1), listOf(0, 1)
        )

        var currentCheckingAreas = mutableSetOf(seedArea)
        var nextCheckingAreas = mutableSetOf<Area>()

        while (true) {
            // 이 와일문 한바퀴 돌때마다 플러드필 한바퀴 돈거인
            for (currentCheckingArea in currentCheckingAreas) {
                val areaX = currentCheckingArea.x
                val areaZ = currentCheckingArea.z

                for (adjacentOffset in adjacentOffsets) {
                    val adjacentArea = getArea(areaX + adjacentOffset[0], areaZ + adjacentOffset[1])
                    adjacentArea.let {
                        checkedAreas.add(adjacentArea!!)
                    }
                    adjacentArea ?: {

                    }
                }
            }
        }
    }
}
