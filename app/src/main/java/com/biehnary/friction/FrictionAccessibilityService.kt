package com.biehnary.friction

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.biehnary.friction.runtime.FrictionRuntime

class
FrictionAccessibilityService : AccessibilityService() {

    private lateinit var runtime: FrictionRuntime

    override fun onServiceConnected() {
        super.onServiceConnected()
        runtime = FrictionRuntime(this)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        if (event == null) return
        runtime.handleEvent(event)
    }

    override fun onInterrupt() {
        // TODO("Not yet implemented")

    }

    override fun onDestroy() {
        super.onDestroy()
        runtime.shutDown()
    }

    fun exitToHome() {
        performGlobalAction(GLOBAL_ACTION_HOME)
    }
}