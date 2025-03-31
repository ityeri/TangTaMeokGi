package com.github.it.tangTaMeokGi.game.area

import com.github.it.tangTaMeokGi.BukkitSyncTaskBatch
import com.github.it.tangTaMeokGi.SubWorldUtils
import com.github.it.tangTaMeokGi.game.GameManager
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import kotlin.random.Random
import kotlinx.coroutines.*
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit

class AreaManager(
    val gameManager: GameManager,
    val mapSize: Int,
    val areaSize: Int
) {

    val plugin = gameManager.plugin
    val world = gameManager.world

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

    fun generate() {

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

        // TODO BukkitBatch 의 타임아웃 빈도수 체크
        // 서버 초기화 하고 테스트 ㄱ

        val scope = CoroutineScope(Dispatchers.Default + Job())

        val batch = BukkitSyncTaskBatch(plugin, 50, 512)

        val jobs: MutableList<Job> = mutableListOf()

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

                val job = scope.launch {
                    area.batchGenerateFrom(batch, world,
                        Random.nextInt(-100000, 100000),
                        Random.nextInt(-100000, 100000)
                    )
                }

                Bukkit.getScheduler().callSyncMethod(plugin) {
                    Bukkit.getServer().sendMessage(Component.text(
                        "[$x, $z] 영역 작업 예약 완료."
                    ))
                }

                jobs.add(job)
            }
        }

        Bukkit.getScheduler().callSyncMethod(plugin) {
            Bukkit.getServer().sendMessage(Component.text(
                "모든 작업 예약 완료. 예약된 작업 완료 대기 시작"
            ))
        }

        for (job in jobs) {
            job.join()
        }
        batch.close()
        batch.start()
        batch.join()
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

    fun getAllArea(): List<Area> {
        val areas: MutableList<Area> = mutableListOf()

        for (line in areaMap) {
            areas.addAll(line)
        }

        return areas.toList()
    }
}
