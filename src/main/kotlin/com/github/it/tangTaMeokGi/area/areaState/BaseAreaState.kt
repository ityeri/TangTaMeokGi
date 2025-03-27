package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area
import org.bukkit.entity.Player

abstract class BaseAreaState(
    val area: Area
) {
    /*
    모든 AreaState 의 기본이 되는 클래스.
    기본적으로 소유자 속성을 가지지 않음
     */

    abstract fun enable()
    abstract fun disable()

    abstract fun update()

    abstract fun onAttackEvent(attackerTeam: Team, attacker: Player)

}