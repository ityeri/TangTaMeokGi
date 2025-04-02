package com.github.it.tangTaMeokGi.userInterface.command

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.area.areaData.OwnerbleAreaData
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

@CommandAlias("map")
@CommandPermission("op")
class MapCommand(val game: Game) : BaseCommand() {

    @Default
    fun onCommand(sender: CommandSender) {
        when (sender) {
            is Entity -> {

            }

            else -> {
                sender.sendMessage("엔티티만 이 명령어 쓸수있음 ㅅㄱ")
            }
        }
    }
}