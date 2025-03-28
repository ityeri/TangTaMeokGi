package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.game.team.Team
import com.github.it.tangTaMeokGi.area.Area
import com.github.it.tangTaMeokGi.area.AreaType
import org.bukkit.entity.Player

class PublicAreaState(area: Area, var isEffectArea: Boolean, areaEffect: AreaEffect? = null) : BaseAreaState(area) {

    override val type = AreaType.PUBLIC_AREA

    override fun onEnable() {}
    override fun onDisable() {}

    override fun update() {
        // TODO areaEffect 로 해당 영역 내에 플레ㅔ이어 한테 효과 넣는거 구현 ㄱ
    }


    override fun onAttackEvent(attackerTeam: Team, attacker: Player) {
        TODO("즉시 확률적으로 attackerTeam 소유의 일반땅 또는 효과땅으로 바뀌는 기능 구현")

    }

}