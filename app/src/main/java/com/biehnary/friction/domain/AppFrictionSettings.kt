package com.biehnary.friction.domain

import com.biehnary.friction.domain.config.DelayIntentConfig
import com.biehnary.friction.domain.config.SessionLimitConfig
import com.biehnary.friction.domain.config.TimeGateConfig

data class AppFrictionSettings(
    val targetPackageName: String,
    val accessMode: AccessMode,

    // friction configs
    val delayIntentConfig: DelayIntentConfig,
    val sessionLimitConfig: SessionLimitConfig,
    val timeGateConfig: TimeGateConfig

)
