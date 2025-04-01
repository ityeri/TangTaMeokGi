package com.github.it.tangTaMeokGi.core.area.areaState

import com.github.it.tangTaMeokGi.core.team.Team
import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.area.AreaType
import com.github.it.tangTaMeokGi.core.event.AreaAttackEvent
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


    override fun onEnable() {
    }

    override fun onDisable() {
    }

    override fun onOwnerTeamWin() {

    }

    override fun onAttackerTeamWin() {

    }

    override fun update() {
        val currentTime = System.currentTimeMillis()/1000
        if (warEndTime <= currentTime) {
            Bukkit.getServer().sendMessage(
                Component.text(
                    "공성전 끝"
                ))
            disable()
        }
    }

    override fun onAttack(areaAttackEvent: AreaAttackEvent) {
        areaAttackEvent.attacker.sendMessage(
            "전쟁 영역에는 점령 시도를 할수 없습니다"
        )
        areaAttackEvent.canceled = true
    }

}