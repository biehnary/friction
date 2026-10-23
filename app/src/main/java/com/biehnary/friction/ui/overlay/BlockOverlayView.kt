package com.biehnary.friction.ui.overlay

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout

fun createBlockOverlay(
    context: Context,
): View {

    val linearLayout = LinearLayout(context)
    linearLayout.setBackgroundColor(Color.BLACK)
    return linearLayout
}