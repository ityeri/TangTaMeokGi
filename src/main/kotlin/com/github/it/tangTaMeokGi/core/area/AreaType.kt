package com.github.it.tangTaMeokGi.core.area

import com.github.it.tangTaMeokGi.core.area.areaState.EmptyAreaData
import com.github.it.tangTaMeokGi.core.area.areaState.PublicAreaData

enum class AreaType {
    EMPTY_AREA {
        override fun setTypeThis(area: Area) {
            area.state = EmptyAreaData(area)
        }
    },


    PUBLIC_AREA {
        override fun setTypeThis(area: Area) {
            area.state = PublicAreaData(area, false)
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