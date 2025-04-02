package com.github.it.tangTaMeokGi.userInterface

import com.github.it.tangTaMeokGi.core.area.AreaManager
import org.bukkit.entity.Player
import org.bukkit.map.*

class AreaMapRenderer(areaManager: AreaManager): MapRenderer() {
    override fun render(mapView: MapView, canvas: MapCanvas, player: Player) {
        println("render 호출")
        // 지도에 원하는 내용 그리기 (텍스트, 색상, 이미지 등)
        canvas.drawText(10, 10, MinecraftFont.Font, "Error!")

        // 특정 위치에 색칠 (픽셀 단위)
        for (x in 30..50) {
            for (y in 30..50) {
                canvas.setPixel(x, y, MapPalette.RED)
            }
        }
    }
}