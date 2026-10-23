package com.biehnary.friction.ui.overlay

import android.content.Context
import android.graphics.PixelFormat
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout

// 하나의 OverlayController는 하나의 Overlay Window만 관리한다.
// Window 제거 후 Controller도 재사용하지 않는다.
class OverlayController(
    private val context: Context,
    private val windowManager: WindowManager,
) {

    private var rootview: ViewGroup? = null

    fun createOverlay() {

        // 중복 방지. Early return
        if (rootview != null) return

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        windowManager.addView(linearLayout, params)

        rootview = linearLayout
    }

    fun changeContent(view: View) {

        val root = requireNotNull(rootview) {
            "Overlay가 생성되지 않았습니다."
        }
        root.removeAllViews()
        root.addView(view, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT))
    }

    fun removeOverlay() {

        val root = rootview ?: return

        windowManager.removeView(root)
        rootview = null
    }
}