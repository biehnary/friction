package com.biehnary.friction.runtime

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.biehnary.friction.domain.ChoiceOption
import com.biehnary.friction.ui.overlay.createChoiceOverlay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


suspend fun choice(
    context: Context,
    windowManager: WindowManager,
    options: List<ChoiceOption>
): ChoiceOption {

    val displayMetrics = context.resources.displayMetrics

    val params = WindowManager.LayoutParams(
        (displayMetrics.widthPixels * 0.8f).toInt(),
        (displayMetrics.heightPixels * 0.8f).toInt(),
        WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT
    ).apply {
        gravity = Gravity.CENTER
    }

    var view: View? = null
    var viewAdded: Boolean = false

    try {
        val choice = suspendCancellableCoroutine<ChoiceOption> { continuation ->
            view = createChoiceOverlay(context, options) { selectedOption ->
                continuation.resume(selectedOption)
            }

            windowManager.addView(view, params)
            viewAdded = true
        }
        return choice
    } finally {
        if (viewAdded) {
            view?.let{windowManager.removeView(it)}
        }
    }
}
