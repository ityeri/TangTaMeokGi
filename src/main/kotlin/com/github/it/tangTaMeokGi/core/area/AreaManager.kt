package com.github.it.tangTaMeokGi.core.area

import com.github.it.tangTaMeokGi.core.BukkitSyncTaskBatch
import com.github.it.tangTaMeokGi.core.SubWorldUtils
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.area.areaData.OccupiableAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.OwnerbleAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.EffectAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.GeneralAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.PublicAreaData
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
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class AreaManager(
    val game: Game,
    val mapSize: Int,
    val areaSize: Int
): GameEventListener, Listener {

    val plugin = game.plugin
    val world = game.world
    var updateTaskId: Int? = null

    var isEnabled = false

    val areaMap: MutableList<MutableList<Area>> = MutableList(mapSize) { mutableListOf() }

    fun isGroundItem(item: ItemStack): Boolean {
        // TODO 이 매서드는 GameManager 로 옮기거나 암튼 더 합리적인 위치로 이동 ㄱ
        return item.type == Material.IRON_AXE
    }

    fun enable() {
        if (isEnabled) { throw IllegalStateException() }
        isEnabled = true

        game.eventDispatcher.register(this)
        Bukkit.getPluginManager().registerEvents(this, plugin)

        updateTaskId = Bukkit.getScheduler().runTaskTimer(plugin,
            Runnable { update() }, 1L, 1L).taskId
    }
    fun disable() {
        if (!isEnabled) { throw IllegalStateException() }
        isEnabled = false

        game.eventDispatcher.unregister(this)
        HandlerList.unregisterAll(this)

        Bukkit.getScheduler().cancelTask(updateTaskId!!)
        updateTaskId = null
    }



    fun setWorldBorder() {
        val totalMapSize: Int = mapSize * areaSize

        world!!.worldBorder.setCenter(totalMapSize / 2.0, totalMapSize / 2.0)

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
        getAllArea().forEach { area ->
            area.update()
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
    fun onAreaOccupation(event: AreaOccupationEvent) {

        val team = event.winningTeam
        // val totalSearchedAreas: MutableSet<Area> = mutableSetOf()


        for (seedArea in getAllArea()) {

            val areas = checkCloseSpace(seedArea.x, seedArea.z, team) ?: continue

            areas.let {
                for (area in areas) {
                    val areaData = area.data

                    when (areaData) {
                        is OccupiableAreaData -> {
                            areaData.occupyBy(team, event.attacker, callEvent = false)
                        }
                    }
                }
                return
            }
        }
    }

    fun checkCloseSpace(seedX: Int, seedY: Int, team: Team): Set<Area>? {
        val seedArea = getArea(seedX, seedY)!!
        val adjacentOffsets: List<List<Int>> = listOf(
            listOf(-1, 0), listOf(1, 0), listOf(0, -1), listOf(0, 1)
        )

        // 순수 울팀 땅이면
        // 그니깐, OwnerbleAreaData 이면서, BaseWarAreaData 가 아니면서
        // 주인 팀이 점령 시도 팀과 동일할경우
        if (seedArea.data is OwnerbleAreaData && seedArea.data !is BaseWarAreaData
            && (seedArea.data as OwnerbleAreaData).ownerTeam == team) {
            // 암것도 안하고 공간이 없다고만 반환
            return null
        }

        var currentSearchingAreas = mutableSetOf(seedArea)
        val nextSearchingAreas = mutableSetOf<Area>()

        // allFinedAreas 는 현재 시드로부터 찾아진 모든 땅
        val allFinedAreas = mutableSetOf(seedArea)

        var isClosed = true

        // 플러드필 루프 하나 (마름모 한겹)
        while (true) {
            // 현재 확인하는 모든 area 를 하나하나 순회 (겉면에 둘러져 있는 땅들)
            for (area in currentSearchingAreas) {

                // area 와 인접한 상하좌우 영역 확인
                for (adjacentOffset in adjacentOffsets) {

                    val adjacentArea = getArea(
                        area.x + adjacentOffset[0],
                        area.z + adjacentOffset[1]
                    )

                    // getArea 를 통해 찾은 adjacentArea 가 null 라는건,
                    // 해당 공간이 닫혀있지 않고, 벽끝까지 닿아있단 의미
                    adjacentArea ?: run { isClosed = false }
                    if (!isClosed) {
                        return null
                    }

                    val areaData = adjacentArea!!.data

                    // 현재 탐색하는 인접한 땅이 현재 시드로 부터 찾은 땅에 이미 포함되 있으면 건너뛰기
                    if (adjacentArea in allFinedAreas) { continue }

                    // 순수 울팀 땅이면
                    // 그니깐, OwnerbleAreaData 이면서, BaseWarAreaData 가 아니면서
                    // 주인 팀이 점령 시도 팀과 동일할경우
                    if (areaData is OwnerbleAreaData && areaData !is BaseWarAreaData
                        && areaData.ownerTeam == team) {
                        // 암것도 하지 않고 다음 인접땅 탐색으로 건너 뜀
                        continue
                    }
                    // 순수 울팀땅이 아니면 (전쟁지역도 포함)
                    else {
                        // 현재 시드로 부터 찾아진 모든 땅에 이 인접땅 추가
                        allFinedAreas += adjacentArea
                        // 다음에 탐색할 땅 목록에 이 인접땅 추가
                        nextSearchingAreas += adjacentArea
                    }
                }

            }

            // 탐색 가능한 모든 공간을 찾아서 다음에 확인할 영역이 없을시
            if (nextSearchingAreas.isEmpty()) {
                break
            }
            else {
                currentSearchingAreas = nextSearchingAreas.toMutableSet()
                nextSearchingAreas.clear()
            }

        }

        return allFinedAreas.toSet()

    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        getAllArea().forEach { area -> area.onPlayerInteract(event) }
    }
}
