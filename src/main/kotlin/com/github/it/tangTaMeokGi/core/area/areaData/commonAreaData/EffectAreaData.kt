package com.github.it.tangTaMeokGi.core.area.areaData.commonAreaData

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import org.bukkit.potion.PotionEffect

open class EffectAreaData(area: Area, ownerTeam: Team,
                          val areaEffect: AreaEffect
) : GeneralAreaData(area, ownerTeam) {

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

    override fun onAttack(areaAttackEvent: AreaAttackEvent) {
        TODO("Not yet implemented")
    }

}