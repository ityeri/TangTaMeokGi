package com.github.it.tangTaMeokGi

import kotlinx.coroutines.Runnable
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

class BukkitSyncTaskBatch(val plugin: Plugin, val timeOutMillis: Int) {
    var isRunning: Boolean = false
    val tasks: MutableList<Runnable> = mutableListOf()

    var taskId: Int? = null

    fun addTask(runnable: Runnable) {
        tasks.add(runnable)
    }

    fun start() {
        if (isRunning) {
            throw RuntimeException("이미 실행중입니다")
        }

        isRunning = true

        taskId = Bukkit.getScheduler().runTaskTimer(plugin,
            Runnable { run() }, 1L, 1L
        ).taskId
    }

    fun stop() {
        if (!isRunning) {
            throw RuntimeException("실행중이지 않습니다")
        }

        Bukkit.getScheduler().cancelTask(taskId!!)

        isRunning = false
    }

    fun join() {
        while (0 < tasks.size) {}
    }

    fun run() {
        val startTime = System.currentTimeMillis()
        val taskAmount = tasks.size


        for (i in 0 until taskAmount) {
            val task: Runnable?
            try {
                task = tasks.removeFirst()
            } catch (e: NoSuchElementException) {
                break
            }

            task.run()

            if (timeOutMillis <= System.currentTimeMillis() - startTime) {
                break
            }
        }
    }
}