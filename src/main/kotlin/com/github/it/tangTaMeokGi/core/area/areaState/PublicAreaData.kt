package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaState.EffectAreaData.AreaEffect
import com.github.it.tangTaMeokGi.core.event.AreaOccupationEvent
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent

class PublicAreaData(area: Area, var isEffectArea: Boolean, val areaEffect: AreaEffect? = null) : BaseAreaData(area) {

    override val type = AreaType.PUBLIC_AREA

    override fun onEnable() {}
    override fun onDisable() {}

    override fun update() {
        // TODO areaEffect 로 해당 영역 내에 플레ㅔ이어 한테 효과 넣는거 구현 ㄱ
    }


    override fun onAttack(areaAttackEvent: AreaAttackEvent) {

        area.game.eventDispatcher.callEvent(
            AreaOccupationEvent(area, null,
                areaAttackEvent.attackerTeam,
                areaAttackEvent.attacker)
        )

        if (isEffectArea) {

            area.data = EffectAreaData(
                area, areaAttackEvent.attackerTeam, areaEffect!!
            )

        } else {
            area.data = GeneralAreaData(
                area, areaAttackEvent.attackerTeam
            )
        }
    }

}