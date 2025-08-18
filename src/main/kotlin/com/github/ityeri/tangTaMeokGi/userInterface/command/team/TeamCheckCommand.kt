package com.github.ityeri.tangTaMeokGi.userInterface.command.team

import co.aikar.commands.PaperCommandManager
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import com.github.ityeri.tangTaMeokGi.core.Game
import com.github.ityeri.tangTaMeokGi.userInterface.command.EnableableCommand
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender
import java.awt.Color

@CommandAlias("teams")
class TeamCheckCommand(val game: Game) : EnableableCommand() {

    val teamManager = game.teamManager
    val mini = MiniMessage.miniMessage()

    override fun enable() {
        val commandManager = PaperCommandManager(game.plugin)
        commandManager.registerCommand(this)
    }

    @Default
    fun onCommand(sender: CommandSender) {

        var message = String()

        teamManager!!.getAllTeam().forEach { team ->
            message += "<#${team.teamColor.toHexString()}>id: ${team.id}, " +
                    "display_name: ${team.displayName}"
        }

        sender.sendMessage(
            mini.deserialize(message)
        )
    }
}

fun Color.toHexString(): String {
    return String.format("#%02x%02x%02x", red, green, blue)
}