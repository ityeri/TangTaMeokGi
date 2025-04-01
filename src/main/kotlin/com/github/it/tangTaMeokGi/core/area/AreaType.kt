package com.github.it.tangTaMeokGi.core.area

import com.github.it.tangTaMeokGi.core.area.areaData.EmptyAreaData
import com.github.it.tangTaMeokGi.core.area.areaData.PublicAreaData

enum class AreaType {
    EMPTY_AREA {
        override fun setTypeThis(area: Area) {
            area.data = EmptyAreaData(area)
        }
    },


    PUBLIC_AREA {
        override fun setTypeThis(area: Area) {
            area.data = PublicAreaData(area, false)
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