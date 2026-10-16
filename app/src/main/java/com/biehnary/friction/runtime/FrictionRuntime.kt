package com.biehnary.friction.runtime

import android.view.accessibility.AccessibilityEvent
import com.biehnary.friction.domain.AccessMode

class FrictionRuntime (private val exit: () -> Unit){

    fun handleEvent(event: AccessibilityEvent) {
        val packageName = event.packageName

        // TODO: packageName으로 AppFrictionSettings 객체 조회 findByPackageName() DB 조회.

        // AppFrictionSettings 있을 때
        if (true) {
            when (/*accessMode*/) {
                AccessMode.FRICTION -> {
                    if (delayItentConfig.enalbed) {
                        delayIntentFriction(delayItentConfig)
                    }

                    if (sessionLimitConfig.enabled) {
                        sessionLimitFriction(sessionLimitConfig)

                    }
                }
                AccessMode.TIME_GATE -> {
                    timeGateFriction(timeGateConfig)
                }

            }
        }
        }

    }
}