package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area

abstract class BaseAreaState(
    val area: Area
) {
    abstract fun update()

    abstract fun onGeneralOccupation(attemptedTeam: Team)

    abstract fun onSpecialOccupation(attemptedTeam: Team)
}