package com.biehnary.friction.runtime

import com.biehnary.friction.domain.config.DelayIntentConfig
import kotlinx.coroutines.delay

suspend fun delayIntentFriction(delayIntentConfig: DelayIntentConfig) {

    delay(delayIntentConfig.delaySeconds * 1000L)

    // TODO choice()



}