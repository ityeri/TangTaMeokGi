package com.github.it.tangTaMeokGi.userInterface.command

import co.aikar.commands.PaperCommandManager
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Values
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.team.Team
import org.bukkit.command.CommandSender
import java.awt.Color

@CommandAlias("newteam")
@CommandPermission("op")
class NewTeamCommand(val game: Game) : EnableableCommand() {

    val teamManager = game.teamManager

    val colorNameMap: Map<String, Color> = mapOf(
        "black" to Color.BLACK,
        "white" to Color.WHITE,
        "red" to Color.RED,
        "green" to Color.GREEN,
        "blue" to Color.BLUE,
        "yellow" to Color.YELLOW,
        "orange" to Color.ORANGE,
        "pink" to Color.PINK,
        "cyan" to Color.CYAN,
        "magenta" to Color.MAGENTA,
        "gray" to Color.GRAY,
        "lightgray" to Color.LIGHT_GRAY,
        "darkgray" to Color.DARK_GRAY,
    )

    override fun enable() {
        val commandManager = PaperCommandManager(game.plugin)
        commandManager.commandCompletions.registerCompletion("color") {
            return@registerCompletion colorNameMap.keys
        }

        commandManager.registerCommand(NewTeamCommand(game))
    }

    @Default
    fun onCommand(sender: CommandSender, teamId: String,
                  @Values("color") teamColorName: String) {
        val teamColor = colorNameMap.get(teamColorName)

        teamColor ?: run {
            sender.sendMessage("색상 이름이 잘못되었습니다!")
        }
        teamColor!!
        teamManager!!

        val newTeam = Team(game, teamId, "test", teamColor)

        try {
            teamManager.addTeam(newTeam)

            sender.sendMessage("새로운 팀 ${newTeam.displayName} 이/가 정상적으로 추가되었습니다!")
        }
        catch (e: IllegalArgumentException) {
            sender.sendMessage("팀 id \"${newTeam.id}\" 가 중복됩니다!")
        }

    }

}