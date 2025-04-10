package com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaData.OwnerbleAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.warAreaData.GeneralWarAreaData
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import com.github.it.tangTaMeokGi.core.event.WarStartEvent

open class GeneralAreaData(area: Area, ownerTeam: Team) : OwnerbleAreaData(area, ownerTeam) {

    override val type = AreaType.GENERAL_AREA

    override fun onEnable() {
    }

    override fun onDisable() {
    }

    override fun update() {
        // 일반땅은 효과 업을세디
    }

    override fun setOwner(team: Team) {

    }

    override fun onAttack(event: AreaAttackEvent) {
        // 자기 팀에 자기가 공격 시도했을 경우
        if (ownerTeam == event.attackerTeam) {
            event.canceled = true
            return
        }

        area.enable()

        area.game.eventDispatcher.callEvent(
            WarStartEvent(area, ownerTeam, event.attackerTeam)
        )
    }

}