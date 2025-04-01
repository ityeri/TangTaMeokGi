package com.github.it.tangTaMeokGi.userInterface

import co.aikar.commands.PaperCommandManager
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.area.areaState.OwnerbleAreaState
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import com.github.it.tangTaMeokGi.core.event.GameEventHandler
import com.github.it.tangTaMeokGi.core.event.GameEventListener

class UserInterface(val game: Game): GameEventListener {
    fun enable() {
        val commandManager = PaperCommandManager(game.plugin)

        commandManager.registerCommand(GetAreaInfoCommand(game))
    }

    @GameEventHandler
    fun onAreaAttack(areaAttackEvent: AreaAttackEvent) {
        when(areaAttackEvent.area.state) {
            is OwnerbleAreaState -> {
                (areaAttackEvent.area.state as OwnerbleAreaState)
                    .ownerTeam.sendMessage("님 땅 ${areaAttackEvent.attackerTeam.displayName}팀의" +
                            "${areaAttackEvent.attacker.name}라는 놈한테 공격맞음 ㅅㄱ")
            }
        }

    }
}