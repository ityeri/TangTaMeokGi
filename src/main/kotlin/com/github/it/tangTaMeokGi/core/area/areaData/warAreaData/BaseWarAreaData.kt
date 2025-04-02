package com.github.it.tangTaMeokGi.core.area.areaData.warAreaData

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.areaData.OwnerbleAreaData
import com.github.it.tangTaMeokGi.core.team.Team

abstract class BaseWarAreaData(area: Area, ownerTeam: Team, val attackerTeam: Team,
                               val warTime: Int):
    OwnerbleAreaData(area, ownerTeam) {

    abstract var warEndTime: Int?

    val timeLeft: Int
        get() = warEndTime!! - (System.currentTimeMillis()/1000).toInt()

    abstract fun onOwnerTeamWin()
    abstract fun onAttackerTeamWin()
    }