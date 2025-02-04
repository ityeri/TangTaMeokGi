package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area

open class GeneralAreaState(area: Area, var ownerTeam: Team? = null) : EmptyAreaState(area) {

    var isOwned: Boolean

    init {
        if (ownerTeam != null) {
            isOwned = true
        } else {
            isOwned = false
        }
    }

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