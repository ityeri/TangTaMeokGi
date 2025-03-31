package com.github.it.tangTaMeokGi.core.event

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.team.Team
import org.bukkit.entity.Player

interface GameEvent

class AttackEvent(attackerTeam: Team, attacker: Player, attackedArea: Area): GameEvent