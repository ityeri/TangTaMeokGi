package com.github.ityeri.tangTaMeokGi.core

import kotlinx.coroutines.Runnable
import kotlinx.coroutines.delay
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

class BukkitSyncTaskBatch(val plugin: Plugin, val timeOutMillis: Int, val maxQueSize: Int) {
    var isRunning: Boolean = false
    var isOpen: Boolean = true
    val taskQue: MutableList<Runnable> = mutableListOf()

    var taskId: Int? = null

    fun addTask(runnable: Runnable) {
        if (!isOpen) {
            throw RuntimeException("batch 가 열려있지 않습니다")
        }

        synchronized(taskQue) {
            taskQue.add(runnable)
        }
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

    fun open() { isOpen = true }
    fun close() { isOpen = false }

    suspend fun join() {
        // while 문이 비어있으면 미친 와일문이 cpu 를 점유해가지고 10밀리초 정도 적당히 딜레이 넣어야함
        while (0 < taskQue.size) {
            delay(10)
        }
    }

    fun run() {
        val startTime = System.currentTimeMillis()
        val taskAmount = taskQue.size


        for (i in 0 until taskAmount) {
            val task: Runnable?
            synchronized(taskQue) {
                task = taskQue.removeFirst()
            }

            if (task == null) {
                continue
            }

            task.run()

            if (timeOutMillis <= System.currentTimeMillis() - startTime) {
                break
            }
        }
    }
}