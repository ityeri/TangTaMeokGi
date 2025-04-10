package com.github.it.tangTaMeokGi.core.area.areaData

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.team.Team

abstract class OwnerbleAreaData(area: Area, val ownerTeam: Team) : BaseAreaData(area) {
    abstract fun setOwner(team: Team)
}