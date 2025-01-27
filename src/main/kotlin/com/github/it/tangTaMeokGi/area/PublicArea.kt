package com.github.it.tangTaMeokGi.area

import org.bukkit.World
import org.bukkit.potion.PotionEffect

class PublicArea(
    world: World,
    x: Int, z: Int,
    width: Int, depth: Int,
    val buffEffect: PotionEffect?, val debuffEffect: PotionEffect?
) : BaseArea(world, x, z, width, depth) {
    val isEffectArea: Boolean
        get() {
            if (buffEffect != null || debuffEffect != null) {
                return true
            } else {
                return false
            }
        }

    // TODO
}