package com.github.it.tangTaMeokGi.userInterface.command

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.userInterface.AreaMapRenderer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.map.MapView

@CommandAlias("map")
@CommandPermission("op")
class MapCommand(val game: Game) : BaseCommand() {

    @Default
    fun onCommand(sender: CommandSender) {
        when (sender) {
            is Player -> {
                val mapItem = ItemStack(Material.FILLED_MAP)
                val mapMeta = (mapItem.itemMeta as MapMeta)

                val view = Bukkit.createMap(sender.world)

                view.isTrackingPosition = true
                view.isUnlimitedTracking = true

                for (renderer in view.renderers) {
                    view.removeRenderer(renderer)
                }

                view.addRenderer(AreaMapRenderer(game.areaManager!!))

                mapMeta.mapView = view
                mapItem.itemMeta = mapMeta

                sender.inventory.addItem(mapItem)

            }

            else -> {
                sender.sendMessage("플레이어만 이 명령어 쓸수있음 ㅅㄱ")
            }
        }
    }
}