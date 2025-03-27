package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area
import org.bukkit.entity.Player

open class GeneralAreaState(area: Area, val ownerTeam: Team) : BaseAreaState(area) {
    override fun enable() {
        TODO("이벤트 리스너 추가 코드")
    }

    override fun disable() {
        TODO("이벤트 리스너 제거")
    }

    override fun update() {
        // 일반땅은 효과 업을세디
    }

    override fun onAttackEvent(attackerTeam: Team, attacker: Player) {
        TODO("Not yet implemented")
    }

}