package com.github.it.tangTaMeokGi.userInterface.command.team

import co.aikar.commands.PaperCommandManager
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandCompletion
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Values
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.userInterface.NamedColor
import com.github.it.tangTaMeokGi.userInterface.command.EnableableCommand
import org.bukkit.command.CommandSender

@CommandAlias("newteam")
@CommandPermission("op")
class NewTeamCommand(val game: Game) : EnableableCommand() {

    val teamManager = game.teamManager

    override fun enable() {
        val commandManager = PaperCommandManager(game.plugin)
        commandManager.registerCommand(NewTeamCommand(game))
    }

    @Default
    @CommandCompletion("@player @teamColor")
    fun onCommand(sender: CommandSender, teamId: String,
                  teamColorName: String) {

        teamManager!!

        val namedColor = NamedColor.getColorFromName(teamColorName)
        namedColor ?: run {
            sender.sendMessage("색상 이름이 잘못되었습니다!")
        }
        namedColor!!

        val teamColor = namedColor.color

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