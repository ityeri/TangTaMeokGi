package com.github.it.tangTaMeokGi.core.area.areaData

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent

open class EmptyAreaData(
    area: Area
) : BaseAreaData(area) {
    /*
    BaseAreaData 의 생성 가능한 클래스.
    기본적으로 아무 기능도 없으며, AreaManager 에서 generate 호출 극초기에
    사용되는 클래스. 사실상 setType 으로 바꾸기 위해 있음.
     */

    override val type = AreaType.EMPTY_AREA

    override fun onEnable() {}
    override fun onDisable() {}

    override fun update() {}

    override fun onAttack(event: AreaAttackEvent) {
        event.attacker.sendMessage("[$type] 공격이 지원되지 않는 땅입니다.")
    }

}