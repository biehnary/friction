package com.biehnary.friction.runtime

import android.content.Context
import android.util.Log
import android.view.WindowManager
import com.biehnary.friction.domain.ChoiceOption
import com.biehnary.friction.domain.config.DelayIntentConfig
import com.biehnary.friction.ui.overlay.OverlayController
import com.biehnary.friction.ui.overlay.createBlockOverlay
import kotlinx.coroutines.delay

suspend fun delayIntentFriction(
    delayIntentConfig: DelayIntentConfig,
    context: Context,
) {

    Log.d("Friction", "새 DelayIntent 시작")
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    val overlayController = OverlayController(context, windowManager)
    overlayController.createOverlay()

    try {
        val blockOverlay = createBlockOverlay(context)
        overlayController.changeContent(blockOverlay)
        delay(delayIntentConfig.delaySeconds * 1000L)

        val options = ChoiceOption.entries.toList()

        choice(context, overlayController, options)
    } finally {

        overlayController.removeOverlay()
    }
}