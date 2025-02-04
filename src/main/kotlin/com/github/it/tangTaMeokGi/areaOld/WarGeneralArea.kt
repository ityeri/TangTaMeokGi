package com.github.it.tangTaMeokGi.areaOld

import com.github.it.tangTaMeokGi.Team
import org.bukkit.World

class WarGeneralArea(
    world: World,
    x: Int, z: Int,
    width: Int, depth: Int,
    val otherTeam: Team, val time: Long
) : GeneralArea(world, x, z, width, depth) {
    // TODO
}