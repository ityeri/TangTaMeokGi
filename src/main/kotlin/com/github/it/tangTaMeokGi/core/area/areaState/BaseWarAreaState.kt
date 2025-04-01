package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.team.Team

abstract class BaseWarAreaState(area: Area, ownerTeam: Team, val attackerTeam: Team,
                                val warTime: Int):
    OwnerbleAreaState(area, ownerTeam) {
    abstract fun onOwnerTeamWin()
    abstract fun onAttackerTeamWin()
    }