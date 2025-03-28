package com.github.it.tangTaMeokGi.game.team

import org.bukkit.entity.Player

class TeamManager {
    private val teamStore: MutableMap<String, Team> = mutableMapOf()

    fun getTeam(id: String): Team? {
        return teamStore.get(id)
    }
    fun getTeam(player: Player): Team? {
        for (team in teamStore.values) {

            if (player.uniqueId in team.playerUUIDs) {
                return team
            }
        }

        return null
    }

    fun addTeam(team: Team) {
        teamStore[team.id] = team
    }
}