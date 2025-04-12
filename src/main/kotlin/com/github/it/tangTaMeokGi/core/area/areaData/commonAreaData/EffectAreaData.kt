package com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaData.OwnerbleAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.warAreaData.EffectWarAreaData
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import com.github.it.tangTaMeokGi.core.event.WarStartEvent
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect

open class EffectAreaData(area: Area, ownerTeam: Team,
                          val potionEffect: AreaPotionEffect
) : OwnerbleAreaData(area, ownerTeam) {

    data class AreaPotionEffect(val buffEffect: PotionEffect? = null, val debuffEffect: PotionEffect? = null)

    override val type = AreaType.EFFECT_AREA

    override fun onEnable() {
    }

    override fun onDisable() {
    }

    override fun update() {
        // TODO "이펙 넣는거 추가 ㄱ"
    }

    override fun occupyBy(team: Team, attacker: Player?, callEvent: Boolean) {
        area.data = EffectAreaData(
            area, team, potionEffect
        )
    }

    override fun onAttack(event: AreaAttackEvent) {
        // 자기 팀에 자기가 공격 시도했을 경우
        if (ownerTeam == event.attackerTeam) {
            event.canceled = true
            return
        }

        area.data = EffectWarAreaData(
            area, ownerTeam, event.attackerTeam, area.game.setting!!.warTime,
            potionEffect
        )

        area.game.eventDispatcher.callEvent(
            WarStartEvent(area, ownerTeam, event.attackerTeam)
        )

        area.enable()
    }

}