package com.github.it.tangTaMeokGi.area

import com.github.it.tangTaMeokGi.area.areaState.BaseAreaState
import com.github.it.tangTaMeokGi.area.areaState.EmptyAreaState
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Item

class Area(
    val areaManager: AreaManager, val world: World,
    val x: Int, val z: Int,
    val width: Int, val depth: Int
) {
    val minX = x * width
    val minZ = z * depth

    val maxX = minX + width
    val maxZ = minZ + depth

    var type: AreaType = AreaType.EMPTY_AREA
    var state: BaseAreaState = EmptyAreaState(this)



    fun setType(areaType: AreaType) {
        areaType.setTypeThis(this)
    }

    fun onGeneralGroundEvent() {
    }

    fun onSpecialGroundEvent() {

    }

    fun update() {
        state.update()

        world.getEntities().filterIsInstance<Item>().filter { itemEntity ->
            // 아이템 엔티티의 위치가 범위 내에 있는지 확인
            val loc = itemEntity.location
            loc.x in minX.toDouble()..maxX.toDouble() && loc.z in minZ.toDouble()..maxZ.toDouble() &&
                    // 아이템이 철 도끼인지 확인
                    itemEntity.itemStack.type == Material.IRON_AXE
        }
    }

    fun regenerateFrom(targetWorld: World, targetX: Int, targetZ: Int, ) {
        val minY: Int
        val maxY: Int

        if (targetWorld.minHeight < world.minHeight) minY = world.minHeight
        else minY = targetWorld.minHeight

        if (world.maxHeight < targetWorld.maxHeight) maxY = world.maxHeight
        else maxY = targetWorld.maxHeight


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

                    thisWorldBlock.type = targetBlock.type
                    thisWorldBlock.blockData = targetBlock.blockData
                    thisWorldBlock.biome = targetBlock.biome
                }
            }
        }
    }



}
