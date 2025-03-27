package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area
import org.bukkit.entity.Player

open class EmptyAreaState(
    area: Area
) : BaseAreaState(area) {
    /*
    BaseAreaState 의 생성 가능한 클래스.
    기본적으로 아무 기능도 없으며, AreaManager 에서 generate 호출 극초기에
    사용되는 클래스. 사실상 setType 으로 바꾸기 위해 있음.
     */
    override fun enable() {}
    override fun disable() {}

    override fun update() {}

    override fun onAttackEvent(attackerTeam: Team, attacker: Player) {}

}