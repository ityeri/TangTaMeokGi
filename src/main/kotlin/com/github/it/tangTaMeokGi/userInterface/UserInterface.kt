package com.github.it.tangTaMeokGi.userInterface

import co.aikar.commands.PaperCommandManager
import com.github.it.tangTaMeokGi.core.Game
import com.github.it.tangTaMeokGi.core.area.areaData.warAreaData.BaseWarAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.OwnerbleAreaData
import com.github.it.tangTaMeokGi.core.event.*
import com.github.it.tangTaMeokGi.userInterface.command.GetAreaInfoCommand

class UserInterface(val game: Game): GameEventListener {
    fun enable() {
        val commandManager = PaperCommandManager(game.plugin)

        commandManager.registerCommand(GetAreaInfoCommand(game))

        game.eventDispatcher.register(this)
    }

    @GameEventHandler
    fun onAreaAttack(areaAttackEvent: AreaAttackEvent) {
        val area = areaAttackEvent.area
        val state = area.data
        val attacker = areaAttackEvent.attacker
        val attackerTeam = areaAttackEvent.attackerTeam

        when(state) {
            is BaseWarAreaData -> {
                attacker.sendMessage(
                    "이미 공성전중인 땅에 공격 못함;;"
                )
            }

            is OwnerbleAreaData -> {
                if (state.ownerTeam == attackerTeam) {
                    attacker.sendMessage(
                        "님팀 땅에 님이 공격할라 하면 어떡함;;"
                    )
                } else {
                    state.ownerTeam.sendMessage(
                        "님 팀의 x ${area.x * area.size} z ${area.z * area.size} 쪽 땅이 " +
                                "${attackerTeam.displayName} 팀의 ${attacker.name} 라는 놈한테 공격맞음 ㅅㄱ")
                }

            }
        }

    }

    @GameEventHandler
    fun onAreaOccupation(areaOccupationEvent: AreaOccupationEvent) {
        val area = areaOccupationEvent.area

        areaOccupationEvent.attacker.sendMessage(
            "x ${area.x * area.size} z ${area.z * area.size} 쪽 땅 점령 성공함 =)"
        )
    }

    @GameEventHandler
    fun onPlayerEnterArea(playerEnterAreaEvent: PlayerEnterAreaEvent) {
        val area = playerEnterAreaEvent.area
        val state = area.data
        val enteredPlayer = playerEnterAreaEvent.player
        val enteredPlayerTeam = game.teamManager!!.getTeam(enteredPlayer)

        enteredPlayerTeam.let {
            when (state) {
                is OwnerbleAreaData -> {
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
        val attackerTeam = warStartEvent.attackerTeam
        val ownerTeam = warStartEvent.ownerTeam

        attackerTeam.sendMessage("공선전 시작! 빨래 제한시간 안에 땅 뺐어야 함")
        ownerTeam.sendMessage("님들 땅에서 공성전 시작됨! 빨리 방어하러 가삼")
    }

    @GameEventHandler
    fun onWarEnd(warEndEvent: WarEndEvent) {
        val attackerTeam = warEndEvent.attackerTeam
        val ownerTeam = warEndEvent.ownerTeam

        if (warEndEvent.isAttackerWin) {
            attackerTeam.sendMessage("ㅊㅋㅊㅋ 님들 땅하나 뺏음")
            ownerTeam.sendMessage("이걸 땅을 뺐기노 흐접")
        }

        else {
            attackerTeam.sendMessage("이걸 땅을 못뺐노 흐접")
            ownerTeam.sendMessage("땅 지켜냄 ㅅㄱ")
        }
    }
}