package com.github.it.tangTaMeokGi.core.area

import com.github.it.tangTaMeokGi.core.BukkitSyncTaskBatch
import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.areaState.BaseAreaState
import com.github.it.tangTaMeokGi.core.area.areaState.EmptyAreaState
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import kotlin.math.ceil
import kotlin.math.floor



class Area(
    val areaManager: AreaManager,
    val x: Int, val z: Int, val size: Int
) : Listener {

    val game = areaManager.game
    val plugin = areaManager.plugin
    val world = areaManager.world

    var isEnabled = false

    val minX = x * size
    val minZ = z * size

    val maxX = minX + size
    val maxZ = minZ + size

    var type = AreaType.EMPTY_AREA
        set(newType) {
            disable()
            field = newType
            newType.setTypeThis(this)
        }

    var state: BaseAreaState = EmptyAreaState(this)
        set(newState) {
            disable()
            field = state
            if (type == state.type) { return }
            type = newState.type
        }



    init {
        type = AreaType.EMPTY_AREA
    }



    fun enable() {
        if (isEnabled) {
            return
        }

        state.enable()
        Bukkit.getServer().pluginManager.registerEvents(this, plugin)
    }
    fun disable() {
        if (!isEnabled) {
            return
        }

        state.disable()
        HandlerList.unregisterAll(this)
    }

    fun update() {
        state.update()
    }

    fun onAttackEvent(attackerTeam: Team, attacker: Player) {
        state.onAttackEvent(attackerTeam, attacker)
    }

    suspend fun generateFrom(targetWorld: World, targetX: Int, targetZ: Int) {

        val minY: Int
        val maxY: Int

        if (targetWorld.minHeight < world.minHeight) minY = targetWorld.minHeight
        else minY = world.minHeight

        if (world.maxHeight < targetWorld.maxHeight) maxY = targetWorld.maxHeight
        else maxY = world.maxHeight


        for (y in minY until maxY) {
            for (z in 0 until size) {
                for (x in 0 until size) {

                    val thisWorldX = minX + x
                    val thisWorldZ = minZ + z

                    val targetWorldX = targetX + x
                    val targetWorldZ = targetZ + z

                    // targetWorld 로부터 복사할 블럭을 가져옴
                    val targetBlock = targetWorld.getBlockAt(targetWorldX, y, targetWorldZ)
                    // world 로부터 붙여넣을 블럭알 가져옴
                    val thisWorldBlock = world.getBlockAt(thisWorldX, y, thisWorldZ)

                    if (targetBlock.type == Material.VOID_AIR) {
                        thisWorldBlock.type = Material.AIR
                    }

                    thisWorldBlock.type = targetBlock.type
                    thisWorldBlock.blockData = targetBlock.blockData
                    thisWorldBlock.biome = targetBlock.biome
                    targetBlock.state.copy(thisWorldBlock.location)


                }
            }
        }

    }

    suspend fun batchGenerateFrom(batch: BukkitSyncTaskBatch,
                          targetWorld: World, targetX: Int, targetZ: Int) {

        val minY: Int
        val maxY: Int

        if (targetWorld.minHeight < world.minHeight) minY = targetWorld.minHeight
        else minY = world.minHeight

        if (world.maxHeight < targetWorld.maxHeight) maxY = targetWorld.maxHeight
        else maxY = world.maxHeight


        for (y in minY until maxY) {
            for (z in 0 until size) {
                for (x in 0 until size) {

                    val thisWorldX = minX + x
                    val thisWorldZ = minZ + z

                    val targetWorldX = targetX + x
                    val targetWorldZ = targetZ + z

                    // targetWorld 로부터 복사할 블럭을 가져옴
                    val targetBlock = targetWorld.getBlockAt(targetWorldX, y, targetWorldZ)
                    // world 로부터 붙여넣을 블럭알 가져옴
                    val thisWorldBlock = world.getBlockAt(thisWorldX, y, thisWorldZ)

                    batch.addTask(Runnable {
                        if (targetBlock.type == Material.VOID_AIR) {
                            thisWorldBlock.type = Material.AIR
                        }

                        thisWorldBlock.type = targetBlock.type
                        thisWorldBlock.blockData = targetBlock.blockData
                        thisWorldBlock.biome = targetBlock.biome
                        targetBlock.state.copy(thisWorldBlock.location)
                    })

                }
            }

        }
    }



    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {

        if (isEntityInArea(event.player) &&
            areaManager.isGroundItem(event.player.itemInHand)) {

            val team = game.teamManager!!.getTeam(event.player)

            team?.let {
                println(type)
                onAttackEvent(
                    team, event.player
                )
            } ?: {
                // 플레이어가 그 어느팀에도 없을경우
                // 암것도 실행하지 않음
            }

        }
    }



    fun getEntities(): List<Entity> {
        // 해당 Area 내부의 모든 엔티티 가져오는 기능 추가
        // 해당 Area 가 포함하는 모든 청크만 일차적으로 가져오고,
        // 이후 가져온 그 청크에서 Area 좌표 밖에 있는 애들은 거르기
        val minChunkX = floor(minX / 16.0).toInt()
        val minChunkZ = floor(minZ / 16.0).toInt()

        val maxChunkX = ceil(maxX / 16.0).toInt()
        val maxChunkZ = ceil(maxZ / 16.0).toInt()

        val entities: MutableList<Entity> = mutableListOf()

        for (chunkZ in minChunkZ until maxChunkZ) {
            for (chunkX in minChunkX until maxChunkX) {
                for (entity in world.getChunkAt(chunkX, chunkZ).entities) {

                    if (isEntityInArea(entity)) {
                        entities.add(entity)
                    }

                }
            }
        }

        return entities
    }

    fun isEntityInArea(entity: Entity): Boolean {
        return (minX <= entity.x && entity.x < maxX &&
                minZ <= entity.z && entity.z < maxZ)
    }

}