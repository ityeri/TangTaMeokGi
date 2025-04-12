package com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaData.BaseAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.OccupiableAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData.EffectAreaData.AreaPotionEffect
import com.github.it.tangTaMeokGi.core.event.AreaOccupationEvent
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import com.github.it.tangTaMeokGi.core.team.Team
import org.bukkit.entity.Player

class PublicAreaData(area: Area, var isEffectArea: Boolean, val potionEffect: AreaPotionEffect? = null) : OccupiableAreaData(area) {

    override val type = AreaType.PUBLIC_AREA

    override fun onEnable() {}
    override fun onDisable() {}

    override fun update() {
        // TODO potionEffect 로 해당 영역 내에 플레ㅔ이어 한테 효과 넣는거 구현 ㄱ
    }

    override fun occupyBy(team: Team, attacker: Player, callEvent: Boolean) {
        if (isEffectArea) {
            area.data = EffectAreaData(
                area, team, potionEffect!!
            )
        }
        else {
            area.data = GeneralAreaData(
                area, team
            )
        }

        if (callEvent) {
            area.game.eventDispatcher.callEvent(
                AreaOccupationEvent(area, null,
                    team, attacker)
            )
        }
    }


    override fun onAttack(event: AreaAttackEvent) {
        occupyBy(event.attackerTeam, event.attacker)
        area.enable()
    }

}