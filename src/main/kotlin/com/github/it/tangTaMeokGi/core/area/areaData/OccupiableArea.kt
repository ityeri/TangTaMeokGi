package com.github.it.tangTaMeokGi.core.area.areaData

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.team.Team
import org.bukkit.entity.Player

abstract class OccupiableArea(area: Area) : BaseAreaData(area) {
    abstract fun occupyBy(team: Team, attacker: Player)
}