package com.biehnary.friction.runtime

import android.content.Context
import android.graphics.PixelFormat
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.biehnary.friction.domain.ChoiceOption
import com.biehnary.friction.ui.overlay.OverlayController
import com.biehnary.friction.ui.overlay.createChoiceOverlay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

suspend fun choice(
    context: Context,
    overlayController: OverlayController,
    options: List<ChoiceOption>
): ChoiceOption {

    val choice = suspendCancellableCoroutine<ChoiceOption> { continuation ->
        val view = createChoiceOverlay(context, options) { selectedOption ->
            continuation.resume(selectedOption)
        }

        Log.d("Friction", "Overlay 추가 직전")
        overlayController.changeContent(view)
        Log.d("Friction", "Overlay 추가 완료")
    }
    return choice
}
