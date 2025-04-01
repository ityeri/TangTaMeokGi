package com.github.it.tangTaMeokGi.userInterface

import co.aikar.commands.PaperCommandManager
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.area.areaState.OwnerbleAreaState
import com.github.it.tangTaMeokGi.core.event.*
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit

class UserInterface(val game: Game): GameEventListener {
    fun enable() {
        val commandManager = PaperCommandManager(game.plugin)

        commandManager.registerCommand(GetAreaInfoCommand(game))

        game.eventDispatcher.register(this)
    }

    @GameEventHandler
    fun onAreaAttack(areaAttackEvent: AreaAttackEvent) {
        val area = areaAttackEvent.area
        val state = area.state
        val attacker = areaAttackEvent.attacker
        val attackerTeam = areaAttackEvent.attackerTeam

        when(state) {
            is OwnerbleAreaState -> {
                state.ownerTeam.sendMessage(
                    "님 팀의 x ${area.x * area.size} z ${area.z * area.size} 쪽 땅이 " +
                    "${attackerTeam.displayName} 팀의 ${attacker.name} 라는 놈한테 공격맞음 ㅅㄱ")
            }
        }

    }

    @GameEventHandler
    fun onPlayerEnterArea(playerEnterAreaEvent: PlayerEnterAreaEvent) {
        val area = playerEnterAreaEvent.area
        val state = area.state
        val enteredPlayer = playerEnterAreaEvent.player
        val enteredPlayerTeam = game.teamManager!!.getTeam(enteredPlayer)

        enteredPlayerTeam.let {
            when (state) {
                is OwnerbleAreaState -> {
                    val ownerTeam = state.ownerTeam
                    ownerTeam.sendMessage(
                        "님들 팀 x ${area.x * area.size} z ${area.z * area.size} 쪽 땅에 " +
                        "${enteredPlayerTeam!!.displayName} 팀의 ${enteredPlayer.name} 이라는 새끼 침입함"
                    )
                }
            }
        }

    }

    @GameEventHandler
    fun onWarStart(warStartEvent: WarStartEvent) {
        Bukkit.getServer().sendMessage(Component.text(
            "공성전 시작"
        ))
    }

    @GameEventHandler
    fun onWarEnd(warEndEvent: WarEndEvent) {
        Bukkit.getServer().sendMessage(Component.text(
                "공성전 끝"
        ))
    }
}