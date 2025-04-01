package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import com.github.it.tangTaMeokGi.core.event.WarStartEvent

open class GeneralAreaState(area: Area, ownerTeam: Team) : OwnerbleAreaState(area, ownerTeam) {

    override val type = AreaType.GENERAL_AREA

    override fun onEnable() {
        // TODO 이벤트 리스너 추가 코드
    }

    override fun onDisable() {
        // TODO 이벤트 리스너 제거
    }

    override fun update() {
        // 일반땅은 효과 업을세디
    }

    override fun onAttack(areaAttackEvent: AreaAttackEvent) {
        // 자기 팀에 자기가 공격 시도했을 경우
        if (ownerTeam == areaAttackEvent.attackerTeam) {
            areaAttackEvent.canceled = true
            return
        }

        area.state = WarGeneralAreaState(
            area, ownerTeam, areaAttackEvent.attackerTeam, area.game.setting!!.warTime
        )
        area.enable()

        area.game.eventDispatcher.callEvent(
            WarStartEvent(area, ownerTeam, areaAttackEvent.attackerTeam)
        )
    }

}