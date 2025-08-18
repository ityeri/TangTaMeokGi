package com.github.ityeri.tangTaMeokGi.core.area.areaData

import com.github.ityeri.tangTaMeokGi.core.area.Area
import com.github.ityeri.tangTaMeokGi.core.area.AreaType
import com.github.ityeri.tangTaMeokGi.core.event.AreaAttackEvent

abstract class BaseAreaData(
    val area: Area
) {
    /*
    모든 AreaState 의 기본이 되는 클래스.
    기본적으로 소유자 속성을 가지지 않음
     */

    val game = area.game
    abstract val type: AreaType

    abstract fun update()

    abstract fun onAttack(event: AreaAttackEvent)

}