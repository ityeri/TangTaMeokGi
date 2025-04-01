package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.event.AttackEvent
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect

open class EffectAreaState(area: Area, ownerTeam: Team,
                           val areaEffect: AreaEffect
) : GeneralAreaState(area, ownerTeam) {

    data class AreaEffect(val buffEffect: PotionEffect? = null, val debuffEffect: PotionEffect? = null)

    override val type = AreaType.EFFECT_AREA

    override fun onEnable() {
        TODO("이벤트 리스너 추가 코드")
    }

    override fun onDisable() {
        TODO("이벤트 리스너 제거")
    }

    override fun update() {
        TODO("이펙 넣는거 추가 ㄱ")
    }

    override fun onAttack(attackEvent: AttackEvent) {
        TODO("Not yet implemented")
    }

}