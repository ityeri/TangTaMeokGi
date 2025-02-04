package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area

class GeneralAreaState(area: Area, var ownerTeam: Team? = null) : EmptyAreaState(area) {

    var isOwned: Boolean

    init {
        if (ownerTeam != null) {
            isOwned = true
        } else {
            isOwned = false
        }
    }

    override fun onGeneralGroundEvent() {

    }

    override fun onSpecialGroundEvent() {

    }
}