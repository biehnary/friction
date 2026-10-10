package com.biehnary.friction

import android.content.Context
import android.content.Intent
import android.util.Log

fun logLaunchableApps(context: Context) {
    val packageManager = context.packageManager
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    val apps = packageManager.queryIntentActivities(intent, 0)

    for (info in apps) {
        val packageName = info.activityInfo.packageName
        Log.d("Launchable Apps", packageName)
    }
}
