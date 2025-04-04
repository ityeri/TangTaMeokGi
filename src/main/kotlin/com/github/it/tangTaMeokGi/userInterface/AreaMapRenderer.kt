package com.github.it.tangTaMeokGi.userInterface

import com.github.it.tangTaMeokGi.core.area.AreaManager
import com.github.it.tangTaMeokGi.core.area.areaData.OwnerbleAreaData
import org.bukkit.entity.Player
import org.bukkit.map.*
import java.awt.Color
import kotlin.math.round


class AreaMapRenderer(val areaManager: AreaManager): MapRenderer(true) {

    companion object {
        fun initRenderer(map: MapView, areaManager: AreaManager) {
            for (renderer in map.renderers) {
                map.removeRenderer(renderer)
            }
            map.addRenderer(AreaMapRenderer(areaManager))
        }
    }

    override fun render(mapView: MapView, canvas: MapCanvas, player: Player) {
        for (z in 0 until areaManager.mapSize) {
            for (x in 0 until areaManager.mapSize) {
                val canvasX = round(128.0 / areaManager.mapSize * x).toInt()
                val canvasY = round(128.0 / areaManager.mapSize * z).toInt()

                val area = areaManager.getArea(x, z)!!
                val areaData = area.data

                val color: Color


                when (areaData) {
                    is OwnerbleAreaData -> {
                        color = areaData.ownerTeam.teamColor
                    }

                    else -> {
                        color = Color.WHITE
                    }
                }

                canvas.setPixelColor(canvasX, canvasY, color)
            }
        }
    }

}


fun MapCanvas.drawRect(x: Int, y: Int, width: Int, height: Int, color: Color) {
    for (dy in y until y + height) {
        for (dx in x until x + width) {
            setPixelColor(dx, dy, color)
        }
    }
}