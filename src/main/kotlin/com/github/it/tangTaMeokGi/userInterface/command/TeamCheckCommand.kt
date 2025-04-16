package com.github.it.tangTaMeokGi.userInterface.command

import co.aikar.commands.annotation.CommandAlias
import com.github.it.tangTaMeokGi.core.Game

@CommandAlias("teams")
class TeamCheckCommand(val game: Game) : EnableableCommand() {
    override fun enable() {

    }
}