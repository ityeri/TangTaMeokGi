package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

open class EffectAreaState(area: Area, ownerTeam: Team,
                           var buffPotionEffect: PotionEffect? = null,
                           var debuffPotionEffect: PotionEffect? = null
) : GeneralAreaState(area, ownerTeam) {

    fun setBuffEffect(effectTypes: Set<PotionEffectType>, effectLevels: Set<Int>) {
        TODO()
    }
    fun setDebuffEffect(effectTypes: Set<PotionEffectType>, effectLevels: Set<Int>) {
        TODO()
    }

    override fun onGeneralOccupation(attemptedTeam: Team) {
        TODO()
    }

    override fun onSpecialOccupation(attemptedTeam: Team) {
        TODO()
    }
}