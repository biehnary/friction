package com.biehnary.friction.domain.config

import java.time.LocalTime

data class TimeGateConfig(
    val startTime: LocalTime,
    val endTime: LocalTime
)
