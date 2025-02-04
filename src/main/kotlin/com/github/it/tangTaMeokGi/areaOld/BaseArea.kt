package com.github.it.tangTaMeokGi.areaOld

import org.bukkit.World

open class BaseArea(
    val world: World,
    val x: Int, val z: Int,
    val width: Int, val depth: Int
) {

    val minX = x * width
    val minZ = z * depth

    val maxX = minX + width
    val maxZ = minZ + depth

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