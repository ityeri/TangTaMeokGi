package com.github.it.tangTaMeokGi.core.area.areaData.warAreaData

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.areaData.OwnerbleAreaData
import com.github.it.tangTaMeokGi.core.team.Team

abstract class BaseWarAreaData(area: Area, ownerTeam: Team, val attackerTeam: Team,
                               val warTime: Int):
    OwnerbleAreaData(area, ownerTeam) {

    var isAtWar: Boolean = false

    var warTimeLeft: Double = -1.0

    abstract fun warStart()

    abstract fun onOwnerTeamWin()
    abstract fun onAttackerTeamWin()

}