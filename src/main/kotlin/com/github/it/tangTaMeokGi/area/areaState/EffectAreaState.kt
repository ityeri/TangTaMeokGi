package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

open class EffectAreaState(area: Area, ownerTeam: Team,
                           val areaEffect: AreaEffect
) : GeneralAreaState(area, ownerTeam) {
    override fun enable() {
        TODO("이벤트 리스너 추가 코드")
    }

    override fun disable() {
        TODO("이벤트 리스너 제거")
    }

    override fun update() {
        TODO("이펙 넣는거 추가 ㄱ")
    }

    override fun onAttackEvent(attackerTeam: Team, attacker: Player) {
        TODO("Not yet implemented")
    }

}