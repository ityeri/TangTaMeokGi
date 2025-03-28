package com.github.it.tangTaMeokGi.area

import com.github.it.tangTaMeokGi.area.areaState.EmptyAreaState
import com.github.it.tangTaMeokGi.area.areaState.PublicAreaState

enum class AreaType {
    EMPTY_AREA {
        override fun setTypeThis(area: Area) {
            area.state = EmptyAreaState(area)
        }
    },



    GENERAL_AREA {
        override fun setTypeThis(area: Area) {
            TODO("Not yet implemented")
        }
    },
    EFFECT_AREA {
        override fun setTypeThis(area: Area) {
            TODO("Not yet implemented")
        }
    },



    PUBLIC_AREA {
        override fun setTypeThis(area: Area) {
            area.state = PublicAreaState(area, false)
        }
    },



    WAR_GENERAL_AREA {
        override fun setTypeThis(area: Area) {
            TODO("Not yet implemented")
        }
    },
    WAR_EFFECT_AREA {
        override fun setTypeThis(area: Area) {
            TODO("Not yet implemented")
        }
    };



    abstract fun setTypeThis(area: Area)
}