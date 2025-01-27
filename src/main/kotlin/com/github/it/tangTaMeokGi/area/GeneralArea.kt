package com.github.it.tangTaMeokGi.area

import com.github.it.tangTaMeokGi.Team
import org.bukkit.World
import org.bukkit.entity.Player

open class GeneralArea(
    world: World,
    x: Int, z: Int,
    width: Int, depth: Int
) : BaseArea(world, x, z, width, depth) {
    var isOwned: Boolean = false
    var ownerTeam: Team? = null

    fun isPlayerInTeam(player: Player): Boolean {
        if (!isOwned) {
            return false
        } else {
            return player.uniqueId in ownerTeam!!.playerUUIDs
        }
    }
    // TODO
}