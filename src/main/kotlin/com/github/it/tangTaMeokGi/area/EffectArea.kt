package com.github.it.tangTaMeokGi.area

import org.bukkit.World
import org.bukkit.entity.Panda.Gene
import org.bukkit.potion.PotionEffect

open class EffectArea(
    world: World,
    x: Int, z: Int,
    width: Int, depth: Int,
    val buffEffect: PotionEffect?, val debuffEffect: PotionEffect?
) : GeneralArea(world, x, z, width, depth) {
    // TODO
}