package com.github.it.tangTaMeokGi

class Task(var isRunning: Boolean=false) {
    fun run() {
        isRunning = true
    }
    fun done() {
        isRunning = false
    }
    fun join() {
        while (isRunning) {}
    }
}