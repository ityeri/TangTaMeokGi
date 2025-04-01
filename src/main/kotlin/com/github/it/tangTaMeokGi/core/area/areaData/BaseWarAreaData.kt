package com.github.it.tangTaMeokGi.core.area.areaData

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.team.Team

abstract class BaseWarAreaData(area: Area, ownerTeam: Team, val attackerTeam: Team,
                               val warTime: Int):
    OwnerbleAreaData(area, ownerTeam) {
    abstract fun onOwnerTeamWin()
    abstract fun onAttackerTeamWin()
    }