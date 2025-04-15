package com.github.it.tangTaMeokGi.core.event

import com.github.it.tangTaMeokGi.core.area.Area
import com.github.it.tangTaMeokGi.core.team.Team
import org.bukkit.entity.Player

interface GameEvent
/*
게임 이벤트는 진짜 취소할게 확실한거 아니면 취소 안되는걸 기본으로 깔고 만드삼
취소 가능한 이벤트로 만드는건 나중에 얼마든지 가능
 */


abstract class CancelableEvent: GameEvent { var canceled = false }

class GameStartEvent: GameEvent
class GameEndEvent(val winningTeams: List<Team>): GameEvent

class AreaAttackEvent(val area: Area,
                      val attackerTeam: Team,
                      val attacker: Player): CancelableEvent()

class AreaOccupationEvent(val area: Area,
                          val losingTeam: Team?,
                          val winningTeam: Team,
                          val attacker: Player?): GameEvent

class WarStartEvent(val area: Area,
                    val ownerTeam: Team,
                    val attackerTeam: Team): GameEvent

class WarEndEvent(val area: Area,
                  val ownerTeam: Team,
                  val attackerTeam: Team,
                  val isAttackerWin: Boolean): GameEvent

class PlayerEnterAreaEvent(val area: Area,
                           val player: Player): GameEvent