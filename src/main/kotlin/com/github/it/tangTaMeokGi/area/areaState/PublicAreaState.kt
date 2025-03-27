package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect

class PublicAreaState(area: Area, areaEffect: AreaEffect? = null) : BaseAreaState(area) {

    override fun enable() {}
    override fun disable() {}

    override fun update() {
        // TODO areaEffect 로 해당 영역 내에 플레ㅔ이어 한테 효과 넣는거 구현 ㄱ
    }


    override fun onAttackEvent(attackerTeam: Team, attacker: Player) {
        TODO("즉시 확률적으로 attackerTeam 소유의 일반땅 또는 효과땅으로 바뀌는 기능 구현")
    }

}