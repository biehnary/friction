package com.biehnary.friction.runtime

import android.view.accessibility.AccessibilityEvent
import com.biehnary.friction.FrictionAccessibilityService
import com.biehnary.friction.domain.AccessMode

class FrictionRuntime(accessibilityService: FrictionAccessibilityService) {

    fun handleEvent(event: AccessibilityEvent) {
        val packageName = event.packageName

        // TODO: packageName으로 AppFrictionSettings 객체 조회 findByPackageName() DB 조회.

        // TODO : AppFrictionSettings 있을 때
//        if (true) {
//            when (받아온 accessMode) {
//                AccessMode.FRICTION -> {
//                    if (delayItentConfig.enalbed) {
//                        delayIntentFriction(delayIntentConfig)
//                    }
//
//                    if (sessionLimitConfig.enabled) {
//                        sessionLimitFriction(sessionLimitConfig)
//
//                    }
//                }
//
//                AccessMode.TIME_GATE -> {
//                    timeGateFriction(timeGateConfig)
//                }
//
//            }
//        }
    }

}
