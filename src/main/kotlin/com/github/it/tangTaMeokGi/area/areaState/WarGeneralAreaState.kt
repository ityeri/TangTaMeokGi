package com.github.it.tangTaMeokGi.area.areaState

import com.github.it.tangTaMeokGi.game.team.Team
import com.github.it.tangTaMeokGi.area.Area
import com.github.it.tangTaMeokGi.area.AreaType
import org.bukkit.entity.Player

class WarGeneralAreaState(
    area: Area,
    ownerTeam: Team,
    val attackerTeam: Team,
    val timeLimitSec: Int

) : GeneralAreaState(area, ownerTeam) {

    override val type = AreaType.WAR_GENERAL_AREA

    var timer: Int = timeLimitSec

    override fun onEnable() {
        TODO("이벤트 리스너 추가 코드")
    }
    override fun onDisable() {
        TODO("이벤트 리스너 제거")
    }

    override fun update() {
        // ㅁㄴㅇㄹ
    }

    override fun onAttackEvent(attackerTeam: Team, attacker: Player) {
        TODO("전장으로 들어오는 공격 이밴트는 씹어야함")
    }



}