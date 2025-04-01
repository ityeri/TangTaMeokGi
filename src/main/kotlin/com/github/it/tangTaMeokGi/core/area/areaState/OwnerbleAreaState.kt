package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.team.Team

abstract class OwnerbleAreaState(area: Area, val ownerTeam: Team) : BaseAreaState(area)