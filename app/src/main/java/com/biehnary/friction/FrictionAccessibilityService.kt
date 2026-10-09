package com.biehnary.friction

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class FrictionAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        Log.d("FrictionAccessibility",
            "type=${event.eventType}, package=${event.packageName}")

        if (event.packageName?.contains("youtube") == true) {
            performGlobalAction(GLOBAL_ACTION_HOME)
        }
    }

    override fun onInterrupt() {
    }
}
