package com.github.it.tangTaMeokGi.userInterface.command.team

import co.aikar.commands.annotation.CommandAlias
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.userInterface.command.EnableableCommand

@CommandAlias("teams")
class TeamCheckCommand(val game: Game) : EnableableCommand() {
    override fun enable() {

    }
}