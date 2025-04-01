package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.area.areaState.EffectAreaState.AreaEffect
import com.github.it.tangTaMeokGi.core.event.AttackEvent
import org.bukkit.entity.Player

class PublicAreaState(area: Area, var isEffectArea: Boolean, val areaEffect: AreaEffect? = null) : BaseAreaState(area) {

    override val type = AreaType.PUBLIC_AREA

    override fun onEnable() {}
    override fun onDisable() {}

    override fun update() {
        // TODO areaEffect 로 해당 영역 내에 플레ㅔ이어 한테 효과 넣는거 구현 ㄱ
    }


    override fun onAttack(attackEvent: AttackEvent) {
        // TODO 즉시 확률적으로 attackerTeam 소유의 일반땅 또는 효과땅으로 바뀌는 기능 구현
        attackEvent.attacker.sendMessage("공격시도 감지함. 해당 팀의 땅으로 바꿈")
        if (isEffectArea) {
            area.state = EffectAreaState(
                area, attackEvent.attackerTeam, areaEffect!!
            )
        } else {
            area.state = GeneralAreaState(
                area, attackEvent.attackerTeam
            )
        }
    }

}