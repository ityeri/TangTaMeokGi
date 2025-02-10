package com.github.it.tangTaMeokGi.area

import com.github.it.tangTaMeokGi.SubWorldUtils
import org.bukkit.World
import kotlin.random.Random

class AreaManager(
    val world: World,
    val mapWidth: Int,
    val mapDepth: Int,
    val areaWidth: Int,
    val areaDepth: Int
) {

    private val areaMap: MutableList<MutableList<Area>> = MutableList(mapDepth) { mutableListOf() }

    init {
        for (z in 0 until  mapDepth) {
            val currentLine = areaMap[z]
            for (x in 0 until  mapWidth) {
                currentLine.add(Area(this, world,
                    x, z, areaDepth, areaDepth
                ))
            }
        }
    }


    fun generate() {
        for (z in 0 until  mapDepth) {
            for (x in 0 until mapWidth) {
                val world: World
                if (Random.nextFloat() < 0.6) {
                    world = SubWorldUtils.getSubOverWorld()
                } else {
                    world = SubWorldUtils.getSubNetherWorld()
                }
                getArea(x, z)!!.regenerateFrom(
                    world, Random.nextInt(-100000, 100000), Random.nextInt(-100000, 100000)
                )
            }
        }
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
}
