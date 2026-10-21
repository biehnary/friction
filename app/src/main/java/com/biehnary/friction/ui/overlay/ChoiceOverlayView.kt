package com.biehnary.friction.ui.overlay

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.biehnary.friction.domain.ChoiceOption

fun createChoiceOverlay(
    context: Context,
    options: List<ChoiceOption>,
    onSelect: (ChoiceOption) -> Unit
): View {

    val linearLayout = LinearLayout(context)
    linearLayout.orientation = LinearLayout.VERTICAL

    options.forEach { option ->
        val button = Button(context)
        button.text = option.name

        button.setOnClickListener {
            onSelect(option)
        }

    linearLayout.addView(button)
    }

    linearLayout.setBackgroundColor(Color.BLACK)

    return linearLayout
}