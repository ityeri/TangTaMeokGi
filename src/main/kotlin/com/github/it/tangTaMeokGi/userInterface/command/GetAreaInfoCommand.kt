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



@CommandAlias("areainfo")
@CommandPermission("op")
class GetAreaInfoCommand(val game: Game) : BaseCommand() {

    @Default
    fun onCommand(sender: CommandSender) {
        when (sender) {
            is Entity -> {
                val area = game.areaManager!!.getArea(sender)

                area.let {
                    area!!
                    sender.sendMessage("""
                        현재 영역 위치: [${area.x}, ${area.z}]
                        영역 종류: ${area.type}
                        영역 활성화 여부: ${area.isEnabled}
                    """.trimIndent())
                    if (area.data is OwnerbleAreaData) {
                        sender.sendMessage("땅 주인팀: ${(area.data as OwnerbleAreaData).ownerTeam.id}")
                    }
                }
                area ?: {
                    sender.sendMessage("그 어떤 영역 안에도 있지 않습니다")
                }
            }

            else -> {
                sender.sendMessage("엔티티만 이 명령어 쓸수있음 ㅅㄱ")
            }
        }
    }
}
