package com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaData.OwnerbleAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.warAreaData.GeneralWarAreaData
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import com.github.it.tangTaMeokGi.core.event.WarStartEvent
import org.bukkit.entity.Player

open class GeneralAreaData(area: Area, ownerTeam: Team) : OwnerbleAreaData(area, ownerTeam) {

    override val type = AreaType.GENERAL_AREA

    override fun update() {}

    override fun occupyBy(team: Team, attacker: Player?, callEvent: Boolean) {
        area.data = GeneralAreaData(
            area, team
        )
    }

    override fun onAttack(event: AreaAttackEvent) {
        // 자기 팀에 자기가 공격 시도했을 경우
        if (ownerTeam == event.attackerTeam) {
            event.canceled = true
            return
        }

        area.data = GeneralWarAreaData(
            area, ownerTeam, event.attackerTeam, area.game.setting!!.warTime
        )

        area.game.eventDispatcher.callEvent(
            WarStartEvent(area, ownerTeam, event.attackerTeam)
        )
    }

}