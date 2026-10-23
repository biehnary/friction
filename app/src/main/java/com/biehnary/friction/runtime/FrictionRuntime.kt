package com.biehnary.friction.runtime

import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.biehnary.friction.FrictionAccessibilityService
import com.biehnary.friction.domain.AccessMode
import com.biehnary.friction.domain.AppFrictionSettings
import com.biehnary.friction.domain.config.DelayIntentConfig
import com.biehnary.friction.domain.config.SessionLimitConfig
import com.biehnary.friction.domain.config.TimeGateConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.time.LocalTime

class FrictionRuntime(private val accessibilityService: FrictionAccessibilityService) {

    private val activeFrictions = mutableMapOf<String, ActiveFriction>()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var foregroundPackageName: String? = null

    fun handleEvent(event: AccessibilityEvent) {

        //Early return, Guard clause
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            return
        }

        val packageName = event.packageName?.toString() ?: return


        if (packageName == accessibilityService.packageName) return
        if (foregroundPackageName == packageName) return

        Log.d(
            "Friction",
            "앱 전환 감지: $foregroundPackageName → $packageName"
        )
        activeFrictions[foregroundPackageName]?.job?.cancel()
        foregroundPackageName = packageName

        // TODO: packageName으로 AppFrictionSettings 객체 조회 findByPackageName() DB 조회.
        // DB 없이 테스트 코드 구현, 타겟 앱 하드 코딩, config 하드 코딩
        val appFrictionSettings = AppFrictionSettings(
            targetPackageName = "com.google.android.youtube",
            accessMode = AccessMode.FRICTION,
            delayIntentConfig = DelayIntentConfig(
                enabled = true,
                delaySeconds = 5
            ),
            sessionLimitConfig = SessionLimitConfig(
                enabled = false,
                intervalMinutes = 0
            ),
            timeGateConfig = TimeGateConfig(
                startTime = LocalTime.MIDNIGHT,
                endTime = LocalTime.MIDNIGHT
            )
        )

        // 실제로 이 조건 분기에서 AppFrictionSettings가 DB에 존재하는지 여부를 따짐.
        if (packageName == appFrictionSettings.targetPackageName) {

            if (activeFrictions[packageName]?.job?.isActive != true) {
                val job = scope.launch {
                    when (appFrictionSettings.accessMode) {
                        AccessMode.FRICTION -> {
                            if (appFrictionSettings.delayIntentConfig.enabled) {
                                delayIntentFriction(
                                    appFrictionSettings.delayIntentConfig,
                                    accessibilityService
                                )
                            }

                            if (appFrictionSettings.sessionLimitConfig.enabled) {
                                sessionLimitFriction(appFrictionSettings.sessionLimitConfig)
                            }
                        }

                        AccessMode.TIME_GATE -> {
                            timeGateFriction(appFrictionSettings.timeGateConfig)
                        }
                    }
                }
                val activeFriction = ActiveFriction(appFrictionSettings.targetPackageName, job)
                activeFrictions.put(appFrictionSettings.targetPackageName, activeFriction)
            }
        }
    }

    fun shutDown() {
        scope.cancel()
    }
}
