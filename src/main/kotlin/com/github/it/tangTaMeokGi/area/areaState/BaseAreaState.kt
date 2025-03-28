package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.game.team.Team
import com.github.it.tangTaMeokGi.area.Area
import com.github.it.tangTaMeokGi.area.AreaType
import org.bukkit.entity.Player

abstract class BaseAreaState(
    val area: Area
) {
    /*
    모든 AreaState 의 기본이 되는 클래스.
    기본적으로 소유자 속성을 가지지 않음
     */

    abstract val type: AreaType

    var isEnabled = false

    fun enable() {
        if (isEnabled) { return }
        isEnabled = true
        onEnable()
    }
    fun disable() {
        if (!isEnabled) { return }
        isEnabled = false
        onDisable()
    }

    abstract fun onEnable()
    abstract fun onDisable()

    abstract fun update()

    abstract fun onAttackEvent(attackerTeam: Team, attacker: Player)

}