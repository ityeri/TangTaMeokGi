package com.github.ityeri.tangTaMeokGi.core.area.areaData

import com.github.ityeri.tangTaMeokGi.core.area.Area
import com.github.ityeri.tangTaMeokGi.core.team.Team

abstract class OwnerbleAreaData(area: Area, val ownerTeam: Team) : OccupiableAreaData(area)