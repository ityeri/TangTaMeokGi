package com.github.it.tangTaMeokGi

import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.WorldType

class SubWorldUtils {
    companion object {
        fun getSubOverWorld(): World {
            var world = Bukkit.getWorld("world_tmp")
            if (world == null) {
                val worldCreator = WorldCreator("world_tmp")
                worldCreator
                    .environment(World.Environment.NORMAL) // 오버, 엔더, 네더 중에 오버월드
                    .type(WorldType.NORMAL)                // 평지나 공허등등 중에 일반월드
                worldCreator.createWorld()

                world = Bukkit.getWorld("world_tmp")
            }

            return world as World
        }

        fun getSubNetherWorld(): World {
            var world = Bukkit.getWorld("world_nether_tmp")
            if (world == null) {
                val worldCreator = WorldCreator("world_nether_tmp")
                worldCreator
                    .environment(World.Environment.NETHER) // 오버, 엔더, 네더 중에 오버월드
                    .type(WorldType.NORMAL)                // 평지나 공허등등 중에 일반월드
                worldCreator.createWorld()

                world = Bukkit.getWorld("world_nether_tmp")
            }

            return world as World
        }
    }
}