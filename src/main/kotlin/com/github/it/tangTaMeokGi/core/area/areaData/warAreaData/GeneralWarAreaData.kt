package com.github.it.tangTaMeokGi.core.area.areaData.warAreaData

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.GeneralAreaData
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import com.github.it.tangTaMeokGi.core.event.WarEndEvent
import org.bukkit.entity.Player

class GeneralWarAreaData(
    area: Area,
    ownerTeam: Team,
    attackerTeam: Team,
    warTime: Int

) : BaseWarAreaData(area, ownerTeam, attackerTeam, warTime) {

    override val type = AreaType.WAR_GENERAL_AREA

    val warEndTime = (System.currentTimeMillis()/1000).toInt() + warTime
    
    val timeLeft: Int
        get() = warEndTime - (System.currentTimeMillis()/1000).toInt()



    override fun onEnable() {
    }

    override fun onDisable() {
    }



    fun onWarEnd() {
        var isAttackerWin = true

        for (entity in area.getEntities()) {

            when (entity) {
                is Player -> {
                    // ownerTeam (원래 주인팀) 소속의 플레이어가 한명이라도 있을경우
                    // 공격자 승리 여부가 false 가 됨
                    if (game.teamManager!!.getTeam(entity) == ownerTeam) {
                        isAttackerWin = false
                        break
                    }
                }
            }

        }

        if (isAttackerWin) {
            onAttackerTeamWin()
        } else {
            onOwnerTeamWin()
        }
    }

    override fun onOwnerTeamWin() {
        area.game.eventDispatcher.callEvent(
            WarEndEvent(
                area, ownerTeam, attackerTeam, false
            )
        )
        area.data = GeneralAreaData(
            area, ownerTeam
        )
        area.enable()
    }

    override fun onAttackerTeamWin() {
        area.game.eventDispatcher.callEvent(
            WarEndEvent(
                area, ownerTeam, attackerTeam, true
            )
        )
        area.data = GeneralAreaData(
            area, attackerTeam
        )
        area.enable()
    }



    override fun update() {
        val currentTime = (System.currentTimeMillis()/1000).toInt()
        if (timeLeft <= 0) {
            onWarEnd()
        }
    }

    override fun onAttack(areaAttackEvent: AreaAttackEvent) {
        // 공성전이 진행중인 땅에 공격을 할순 없음
        areaAttackEvent.canceled = true
    }

}