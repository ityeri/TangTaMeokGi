package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.event.AttackEvent
import org.bukkit.entity.Player

open class GeneralAreaState(area: Area, val ownerTeam: Team) : BaseAreaState(area) {

    override val type = AreaType.GENERAL_AREA

    override fun onEnable() {
        TODO("이벤트 리스너 추가 코드")
    }

    override fun onDisable() {
        TODO("이벤트 리스너 제거")
    }

    override fun update() {
        // 일반땅은 효과 업을세디
    }

    override fun onAttack(attackEvent: AttackEvent) {
        TODO("Not yet implemented")
    }

}