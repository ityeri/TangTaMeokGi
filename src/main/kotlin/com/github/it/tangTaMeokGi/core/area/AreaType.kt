package com.github.it.tangTaMeokGi.core.area

import com.github.it.tangTaMeokGi.core.area.areaState.EmptyAreaState
import com.github.it.tangTaMeokGi.core.area.areaState.PublicAreaState

enum class AreaType {
    EMPTY_AREA {
        override fun setTypeThis(area: Area) {
            area.state = EmptyAreaState(area)
        }
    },


    PUBLIC_AREA {
        override fun setTypeThis(area: Area) {
            area.state = PublicAreaState(area, false)
        }
    },


    GENERAL_AREA {
        override fun setTypeThis(area: Area) {
            // TODO
        }
    },
    EFFECT_AREA {
        override fun setTypeThis(area: Area) {
            // TODO
        }
    },


    WAR_GENERAL_AREA {
        override fun setTypeThis(area: Area) {
            // TODO
        }
    },
    WAR_EFFECT_AREA {
        override fun setTypeThis(area: Area) {
            // TODO
        }
    };



    abstract fun setTypeThis(area: Area)
}