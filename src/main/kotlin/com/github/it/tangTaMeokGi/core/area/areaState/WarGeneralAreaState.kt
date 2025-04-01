package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
import com.github.it.tangTaMeokGi.core.event.WarEndEvent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit

class WarGeneralAreaState(
    area: Area,
    ownerTeam: Team,
    attackerTeam: Team,
    warTime: Int

) : BaseWarAreaState(area, ownerTeam, attackerTeam, warTime) {

    override val type = AreaType.WAR_GENERAL_AREA

    val warEndTime = (System.currentTimeMillis()/1000).toInt() + warTime
    
    val timeLeft: Int
        get() = (System.currentTimeMillis()/1000).toInt() - warEndTime


    override fun onEnable() {
    }

    override fun onDisable() {
    }

    override fun onOwnerTeamWin() {

    }

    override fun onAttackerTeamWin() {
        area.game.eventDispatcher.callEvent(
            WarEndEvent(
                area, ownerTeam, attackerTeam, true
            )
        )
        area.state = GeneralAreaState(
            area, attackerTeam
        )
        area.enable()
    }

    override fun update() {
        Bukkit.getServer().sendMessage(
            Component.text(timeLeft)
        )
        val currentTime = (System.currentTimeMillis()/1000).toInt()
        if (warEndTime <= currentTime) {
            onAttackerTeamWin()
        }
    }

    override fun onAttack(areaAttackEvent: AreaAttackEvent) {
        // TODO 공성전 지역에 점령 시도시 점령 불가 메세지 전송을 UserInterface 로 넘기기
        areaAttackEvent.attacker.sendMessage(
            "전쟁 영역에는 점령 시도를 할수 없습니다"
        )
        areaAttackEvent.canceled = true
    }

}