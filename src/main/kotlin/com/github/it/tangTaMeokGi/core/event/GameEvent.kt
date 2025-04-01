package com.github.it.tangTaMeokGi.core.event

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.team.Team
import org.bukkit.entity.Player

interface GameEvent

class PlayerEnterAreaEvent(val area: Area, val player: Player): GameEvent


abstract class CancelableEvent: GameEvent { var canceled = false }

class AttackEvent(val attackerTeam: Team, val attacker: Player, val attackedArea: Area): CancelableEvent()