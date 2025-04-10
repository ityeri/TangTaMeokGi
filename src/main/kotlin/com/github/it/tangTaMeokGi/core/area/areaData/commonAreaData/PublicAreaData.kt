package com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaData.BaseAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.EffectAreaData.AreaPotionEffect
import com.github.it.tangTaMeokGi.core.event.AreaOccupationEvent
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent

class PublicAreaData(area: Area, var isEffectArea: Boolean, val potionEffect: AreaPotionEffect? = null) : BaseAreaData(area) {

    override val type = AreaType.PUBLIC_AREA

    override fun onEnable() {}
    override fun onDisable() {}

    override fun update() {
        // TODO potionEffect 로 해당 영역 내에 플레ㅔ이어 한테 효과 넣는거 구현 ㄱ
    }


    override fun onAttack(event: AreaAttackEvent) {

        if (isEffectArea) {
            area.data = EffectAreaData(
                area, event.attackerTeam, potionEffect!!
            )
        }
        else {
            area.data = GeneralAreaData(
                area, event.attackerTeam
            )
        }

        area.game.eventDispatcher.callEvent(
            AreaOccupationEvent(area, null,
                event.attackerTeam,
                event.attacker)
        )

        area.enable()
    }

}