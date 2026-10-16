package com.biehnary.friction.runtime

import com.biehnary.friction.domain.config.TimeGateConfig
import java.time.LocalTime

fun timeGateFriction(timeGateConfig: TimeGateConfig) {

    val startTime = timeGateConfig.startTime
    val endTime = timeGateConfig.endTime
    val now = LocalTime.now()

    if (startTime <= now && now < endTime) {
        // TODO : exit()
        // showMessage()
    } else if (startTime > endTime && (now >= startTime || now < endTime)) {
        // TODO : exit()
        // showMessage()
    }
}