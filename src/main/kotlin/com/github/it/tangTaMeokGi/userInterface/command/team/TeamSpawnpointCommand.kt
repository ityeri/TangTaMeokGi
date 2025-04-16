package com.github.it.tangTaMeokGi.userInterface.command.team

import co.aikar.commands.PaperCommandManager
import co.aikar.commands.annotation.CommandAlias
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.userInterface.command.EnableableCommand

@CommandAlias("set")
class TeamSpawnpointCommand(val game: Game) : EnableableCommand() {

    override fun enable() {
        val commandManager = PaperCommandManager(game.plugin)
    }

}