package com.github.it.tangTaMeokGi

import kotlinx.coroutines.Runnable
import kotlinx.coroutines.delay
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import kotlin.random.Random

class BukkitSyncTaskBatch(val plugin: Plugin, val timeOutMillis: Int) {
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
        // 쓰레드나 비동기 관련 이슈로 delay 가 있어야 블로킹이 안걸림
        while (0 < taskQue.size) { delay(0) }
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