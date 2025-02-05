package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area
import org.bukkit.potion.PotionEffect

class WarEffectAreaState(area: Area, ownerTeam: Team, val enemyTeam: Team, val timeLimitSec: Int,
                         buffPotionEffect: PotionEffect? = null,
                         debuffPotionEffect: PotionEffect? = null
) : EffectAreaState(area, ownerTeam, buffPotionEffect, debuffPotionEffect) {

    var timer: Int = timeLimitSec

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun onGeneralOccupation(attemptedTeam: Team) {
        TODO()
    }

    override fun onSpecialOccupation(attemptedTeam: Team) {
        TODO()
    }

}