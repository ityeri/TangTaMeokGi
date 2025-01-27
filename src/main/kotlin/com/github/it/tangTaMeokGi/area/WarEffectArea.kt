package com.github.it.tangTaMeokGi.area

import com.github.it.tangTaMeokGi.Team
import org.bukkit.World
import org.bukkit.potion.PotionEffect

class WarEffectArea(
    world: World,
    x: Int, z: Int,
    width: Int, depth: Int,
    buffEffect: PotionEffect?, debuffEffect: PotionEffect?,
    val otherTeam: Team, val time: Long
) : EffectArea(world, x, z, width, depth, buffEffect, debuffEffect) {
    // TODO
}