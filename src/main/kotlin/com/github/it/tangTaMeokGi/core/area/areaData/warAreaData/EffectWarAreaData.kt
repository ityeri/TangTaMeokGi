package com.github.it.tangTaMeokGi.core.area.areaData.warAreaData

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.EffectAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.EffectAreaData.AreaPotionEffect
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import com.github.it.tangTaMeokGi.core.event.WarEndEvent
import org.bukkit.entity.Player

class EffectWarAreaData(
    area: Area,
    ownerTeam: Team,
    attackerTeam: Team,
    timeLimitSec: Int,
    val potionEffect: AreaPotionEffect
) : BaseWarAreaData(area, ownerTeam, attackerTeam, timeLimitSec) {

    override val type = AreaType.WAR_EFFECT_AREA
    override var warEndTime: Int? = null


    override fun onEnable() {
        warEndTime = (System.currentTimeMillis()/1000).toInt() + warTime
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
        area.data = EffectAreaData(
            area, ownerTeam, potionEffect
        )
        area.enable()
    }

    override fun onAttackerTeamWin() {
        area.game.eventDispatcher.callEvent(
            WarEndEvent(
                area, ownerTeam, attackerTeam, true
            )
        )
        area.data = EffectAreaData(
            area, attackerTeam, potionEffect
        )
        area.enable()
    }


    override fun update() {
        if (timeLeft <= 0) {
            onWarEnd()
        }
    }
    override fun onAttack(areaAttackEvent: AreaAttackEvent) {
        // 공성전이 진행중인 땅에 공격을 할순 없음
        areaAttackEvent.canceled = true
    }

}