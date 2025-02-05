package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area

class WarGeneralAreaState(area: Area, ownerTeam: Team, val enemyTeam: Team, val timeLimitSec: Int
) : GeneralAreaState(area, ownerTeam) {

    var timer: Int = timeLimitSec

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun onGeneralOccupation(attemptedTeam: Team) {
        TODO()
    }

    override fun onSpecialOccupation(attemptedTeam: Team) {
        TODO()
    }

}