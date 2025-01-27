package com.github.it.tangTaMeokGi

import org.bukkit.World

class Area(
    val x: Int,
    val z: Int,
    val width: Int,
    val depth: Int
) {

    val minX = x * width
    val minZ = z * depth

    val maxX = minX + width
    val maxZ = minZ + depth

    fun regenerateFrom(targetX: Int, targetZ: Int, world: World) {
        // TODO
    }
}