package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.event.AttackEvent
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

    protected abstract fun onEnable()
    protected abstract fun onDisable()

    abstract fun update()

    abstract fun onAttack(attackEvent: AttackEvent)

}