package com.github.it.tangTaMeokGi.userInterface

import com.github.it.tangTaMeokGi.core.area.AreaManager
import org.bukkit.entity.Player
import org.bukkit.map.*
import java.awt.Color


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
        canvas.drawText(10, 10, MinecraftFont.Font, "Test!")

        // 특정 위치에 색칠 (픽셀 단위)
        for (x in 30..50) {
            for (y in 30..50) {
                canvas.setPixelColor(x, y, Color.WHITE)
            }
        }

        player.sendMessage("AreaManager.render")
    }

}