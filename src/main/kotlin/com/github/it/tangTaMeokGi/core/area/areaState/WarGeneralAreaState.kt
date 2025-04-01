package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent

class WarGeneralAreaState(
    area: Area,
    ownerTeam: Team,
    attackerTeam: Team,
    warTime: Int

) : BaseWarAreaState(area, ownerTeam, attackerTeam, warTime) {

    override val type = AreaType.WAR_GENERAL_AREA

    val warEndTime = (System.currentTimeMillis()/1000).toInt() + warTime


    override fun onEnable() {
        // TODO 이벤트 리스너 추가 코드
    }
    override fun onDisable() {
        // TODO 이벤트 리스너 제거
    }

    override fun update() {
        val currentTime = System.currentTimeMillis()/1000
        if (warEndTime <= currentTime) {
            disable()
            println("전쟁 중단됨")
        }
    }

    override fun onAttack(areaAttackEvent: AreaAttackEvent) {
        areaAttackEvent.attacker.sendMessage(
            "전쟁 영역에는 점령 시도를 할수 없습니다"
        )
        areaAttackEvent.canceled = true
    }

}