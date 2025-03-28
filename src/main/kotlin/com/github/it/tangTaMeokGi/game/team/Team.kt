package com.github.it.tangTaMeokGi.game.team

import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.*

class Team(
    val teamId: String, val displayName: String,
    val teamColor: Color, val isEffectivePossible: Boolean = false
) {
    val playerUUIDs: MutableSet<UUID> = mutableSetOf()

    val onlinePlayers: MutableSet<Player>
        get() {
            val playerSet: MutableSet<Player> = mutableSetOf()
            for (playerUUID in playerUUIDs) {
                val player = Bukkit.getPlayer(playerUUID)
                if (player != null) {
                    playerSet.add(player)
                }
            }

            return playerSet
        }

    val offlinePlayers: MutableSet<OfflinePlayer>
        get() {
            val playerSet: MutableSet<OfflinePlayer> = mutableSetOf()

            for (playerUUID in playerUUIDs) {
                val offlinePlayer = Bukkit.getOfflinePlayer(playerUUID)
                if (!offlinePlayer.isOnline) {
                    playerSet.add(offlinePlayer)
                }
            }

            return playerSet
        }


    fun addPlayer(player: Player) {
        playerUUIDs.add(player.uniqueId)
    }

    fun rmPlayer(player: Player): Boolean {
        return playerUUIDs.remove(player.uniqueId)
    }
}