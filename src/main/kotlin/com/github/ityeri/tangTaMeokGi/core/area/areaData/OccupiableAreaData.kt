package com.github.ityeri.tangTaMeokGi.core.area.areaData

import com.github.ityeri.tangTaMeokGi.core.area.Area
import com.github.ityeri.tangTaMeokGi.core.team.Team
import org.bukkit.entity.Player

abstract class OccupiableAreaData(area: Area) : BaseAreaData(area) {
    abstract fun occupyBy(team: Team, attacker: Player?, callEvent: Boolean = true)
}