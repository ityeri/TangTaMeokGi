package com.github.it.tangTaMeokGi.core.event

import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.isAccessible

class GameEventDispatcher {
    private val listeners = mutableListOf<GameEventListener>()

    fun register(listener: GameEventListener) {
        listeners.add(listener)
    }

    fun unregister(listener: GameEventListener) {
        listeners.remove(listener)
    }

    fun callEvent(event: GameEvent) {
        for (listener in listeners) {
            // listener 의 모든 메서드 가져옴
            val methods = listener::class.declaredFunctions

            for (method in methods) {
                val annotation = method.findAnnotation<GameEventHandler>()
                if (annotation != null && method.parameters.size == 2 && method.parameters[1].type.classifier == event::class) {
                    method.isAccessible = true
                    method.call(listener, event)
                }
            }
        }
    }
}