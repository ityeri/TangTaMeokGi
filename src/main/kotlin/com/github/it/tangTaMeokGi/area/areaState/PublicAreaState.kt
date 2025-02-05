package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area
import org.bukkit.potion.PotionEffect

class PublicAreaState(area: Area, val previousOwner: Team, val coolTimeSec: Int, val isEffectArea: Boolean = false,
                      val buffPotionEffect: PotionEffect? = null,
                      val debuffPotionEffect: PotionEffect? = null
) : EmptyAreaState(area) {

    var timer: Int = coolTimeSec

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