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

@CommandAlias("map")
@CommandPermission("op")
class MapCommand(val game: Game) : BaseCommand() {

    @Default
    fun onCommand(sender: CommandSender) {
        when (sender) {
            is Player -> {
                val map = sender.inventory.getItem(0)!!
                val view = (map.itemMeta as MapMeta).mapView!!

                view.removeRenderer(view.renderers[0])

                view.addRenderer(AreaMapRenderer(game.areaManager!!))

                println(view.renderers)

//                val map = ItemStack(Material.FILLED_MAP)
//                val view = Bukkit.createMap(sender.world)
//
//                AreaMapRenderer.initRenderer(view, game.areaManager!!)
//
//                (map.itemMeta as MapMeta).mapView = view
//
//                sender.inventory.addItem(map) // 유저에게 지도 지급
//
//                sender.sendMap(view)

            }

            else -> {
                sender.sendMessage("플레이어만 이 명령어 쓸수있음 ㅅㄱ")
            }
        }
    }
}