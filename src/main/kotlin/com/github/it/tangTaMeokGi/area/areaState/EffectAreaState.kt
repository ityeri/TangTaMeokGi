package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.game.team.Team
import com.github.it.tangTaMeokGi.area.Area
import com.github.it.tangTaMeokGi.area.AreaType
import org.bukkit.entity.Player

open class EffectAreaState(area: Area, ownerTeam: Team,
                           val areaEffect: AreaEffect
) : GeneralAreaState(area, ownerTeam) {

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

    override fun onAttackEvent(attackerTeam: Team, attacker: Player) {
        TODO("Not yet implemented")
    }

}