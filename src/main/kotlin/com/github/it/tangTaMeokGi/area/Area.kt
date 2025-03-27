package com.github.it.tangTaMeokGi.area

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.areaState.BaseAreaState
import com.github.it.tangTaMeokGi.area.areaState.EmptyAreaState
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Area(
    val areaManager: AreaManager, val world: World, val plugin: JavaPlugin,
    val x: Int, val z: Int,
    val width: Int, val depth: Int
) {
    val minX = x * width
    val minZ = z * depth

    val maxX = minX + width
    val maxZ = minZ + depth

    var type = AreaType.EMPTY_AREA
    var state: BaseAreaState = EmptyAreaState(this)



    fun setTypeTo(areaType: AreaType) {
        areaType.setTypeThis(this)
    }

    fun onAttackEvent(attackerTeam: Team, attacker: Player) {
        state.onAttackEvent(attackerTeam, attacker)
    }

    fun update() {
        state.update()
        // TODO 도끼를 통한 점령 시도를 이 코드에서 식별함
    }

    fun regenerateFrom(targetWorld: World, targetX: Int, targetZ: Int, ) {
        val minY: Int
        val maxY: Int

        if (targetWorld.minHeight < world.minHeight) minY = targetWorld.minHeight
        else minY = world.minHeight

        if (world.maxHeight < targetWorld.maxHeight) maxY = targetWorld.maxHeight
        else maxY = world.maxHeight


        for (y in minY until maxY) {
            for (z in 0 until depth) {
                for (x in 0 until width) {
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


                }
            }
        }
    }

    fun getEntity() {
        TODO("해당 Area 내부의 모든 엔티티 가져오는 기능 추가")
        // 해당 Area 가 포함하는 모든 청크만 일차적으로 가져오고,
        // 이후 가져온 그 청크에서 Area 좌표 밖에 있는 애들은 거르기
    }
}