package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.area.Area

abstract class BaseAreaState(
    val area: Area
) {
    abstract fun onGeneralGroundEvent()

    abstract fun onSpecialGroundEvent()
}