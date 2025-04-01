package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import com.github.it.tangTaMeokGi.core.event.WarEndEvent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit

class WarGeneralAreaState(
    area: Area,
    ownerTeam: Team,
    attackerTeam: Team,
    warTime: Int

) : BaseWarAreaState(area, ownerTeam, attackerTeam, warTime) {

    override val type = AreaType.WAR_GENERAL_AREA

    val warEndTime = (System.currentTimeMillis()/1000).toInt() + warTime
    
    val timeLeft: Int
        get() = warEndTime - (System.currentTimeMillis()/1000).toInt()


    override fun onEnable() {
    }

    override fun onDisable() {
    }

    override fun onOwnerTeamWin() {
        area.game.eventDispatcher.callEvent(
            WarEndEvent(
                area, ownerTeam, attackerTeam, false
            )
        )
        area.state = GeneralAreaState(
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
        area.state = GeneralAreaState(
            area, attackerTeam
        )
        area.enable()
    }

    override fun update() {
        val currentTime = (System.currentTimeMillis()/1000).toInt()
        if (timeLeft <= 0) {
            onAttackerTeamWin()
        }
    }

    override fun onAttack(areaAttackEvent: AreaAttackEvent) {
        // 공성전이 진행중인 땅에 공격을 할순 없음
        areaAttackEvent.canceled = true
    }

}