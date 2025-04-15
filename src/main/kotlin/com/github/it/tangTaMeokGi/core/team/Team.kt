package com.github.it.tangTaMeokGi.core.team

import com.github.it.tangTaMeokGi.core.Game
import com.mojang.brigadier.Message
import org.bukkit.Bukkit
import java.awt.Color
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.*

class Team(val game: Game,
    val id: String, val displayName: String,
    val teamColor: Color
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


    fun sendMessage(message: String) {
        for (player in onlinePlayers) {
            player.sendMessage(message)
        }
    }


    fun addPlayer(player: Player) {
        playerUUIDs.add(player.uniqueId)
    }

    fun rmPlayer(player: Player): Boolean {
        return playerUUIDs.remove(player.uniqueId)
    }
}