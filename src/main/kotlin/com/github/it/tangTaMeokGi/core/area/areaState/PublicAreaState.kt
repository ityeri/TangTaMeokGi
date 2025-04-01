package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaState.EffectAreaState.AreaEffect
import com.github.it.tangTaMeokGi.core.event.AreaOccupationEvent
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent

class PublicAreaState(area: Area, var isEffectArea: Boolean, val areaEffect: AreaEffect? = null) : BaseAreaState(area) {

    override val type = AreaType.PUBLIC_AREA

    override fun onEnable() {}
    override fun onDisable() {}

    override fun update() {
        // TODO areaEffect 로 해당 영역 내에 플레ㅔ이어 한테 효과 넣는거 구현 ㄱ
    }


    override fun onAttack(areaAttackEvent: AreaAttackEvent) {

        area.game.eventDispatcher.callEvent(
            AreaOccupationEvent(null,
                areaAttackEvent.attackerTeam,
                areaAttackEvent.attacker, area)
        )

        if (isEffectArea) {

            area.state = EffectAreaState(
                area, areaAttackEvent.attackerTeam, areaEffect!!
            )

        } else {
            area.state = GeneralAreaState(
                area, areaAttackEvent.attackerTeam
            )
        }
    }

}