package com.github.it.tangTaMeokGi.areaOld

import org.bukkit.World

class AreaManager(
    val world: World,
    val mapWidth: Int,
    val mapDepth: Int,
    val areaWidth: Int,
    val areaDepth: Int
) {

    private val areaMap: MutableList<MutableList<BaseArea>> = MutableList(mapDepth) { mutableListOf() }

    init {
        for (z in 0 until  mapDepth) {
            val currentLine = areaMap.get(z)
            for (x in 0 until  mapWidth) {
                currentLine.add(BaseArea(world,
                    x*areaWidth, z*areaDepth, areaDepth, areaDepth
                ))
            }
        }
    }


    fun generate() {

    }


    fun getArea(x: Int, z: Int): BaseArea? {
        try {
            return areaMap[z][x]
        } catch (e: IndexOutOfBoundsException) {
            return null
        }
    }

    fun setArea(x: Int, z: Int, newArea: BaseArea) {
        areaMap[z][x] = newArea
    }
}
