package com.biehnary.friction

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.biehnary.friction.runtime.FrictionRuntime

class FrictionAccessibilityService : AccessibilityService() {

    private lateinit var runtime: FrictionRuntime

    override fun onServiceConnected() {
        super.onServiceConnected()
        runtime = FrictionRuntime(this)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // TODO("Not yet implemented")
    }

    override fun onInterrupt() {
        // TODO("Not yet implemented")
    }

    fun exitToHome() {
        performGlobalAction(GLOBAL_ACTION_HOME)
    }
}