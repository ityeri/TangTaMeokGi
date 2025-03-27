package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.Team
import com.github.it.tangTaMeokGi.area.Area
import org.bukkit.entity.Player

class WarGeneralAreaState(
    area: Area,
    ownerTeam: Team,
    val attackerTeam: Team,
    val timeLimitSec: Int

) : GeneralAreaState(area, ownerTeam) {

    var timer: Int = timeLimitSec

    override fun enable() {
        TODO("이벤트 리스너 추가 코드")
    }
    override fun disable() {
        TODO("이벤트 리스너 제거")
    }

    override fun update() {
        // ㅁㄴㅇㄹ
    }

    override fun onAttackEvent(attackerTeam: Team, attacker: Player) {
        TODO("전장으로 들어오는 공격 이밴트는 씹어야함")
    }



}