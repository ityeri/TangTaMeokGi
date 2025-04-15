package com.github.it.tangTaMeokGi.userInterface.command

import co.aikar.commands.BaseCommand
import co.aikar.commands.PaperCommandManager
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import com.github.it.tangTaMeokGi.core.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import org.bukkit.command.CommandSender

@CommandAlias("mapgenerate")
@CommandPermission("op")
class MapGenerateCommand(val game: Game, val scope: CoroutineScope) : BaseCommand() {

    fun enable() {
        val commandManager = PaperCommandManager(game.plugin)
        commandManager.registerCommand(this)
    }

    @Default
    fun onCommand(sender: CommandSender) {
        scope.launch {
            game.mapGenerate()
        }
    }
}