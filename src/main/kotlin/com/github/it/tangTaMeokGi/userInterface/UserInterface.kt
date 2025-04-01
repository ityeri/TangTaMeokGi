package com.github.it.tangTaMeokGi.userInterface

import co.aikar.commands.PaperCommandManager
import com.github.it.tangTaMeokGi.core.Game

class UserInterface(val game: Game) {
    fun enable() {
        val commandManager = PaperCommandManager(game.plugin)

        commandManager.registerCommand(GetAreaInfoCommand(game))
    }
}