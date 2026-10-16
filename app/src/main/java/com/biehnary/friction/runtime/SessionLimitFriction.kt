package com.biehnary.friction.runtime

import com.biehnary.friction.domain.config.SessionLimitConfig
import kotlinx.coroutines.delay

suspend fun sessionLimitFriction(sessionLimitConfig: SessionLimitConfig) {

    delay(sessionLimitConfig.intervalMinutes * 60_000L)

    /*
    TODO:
    while(choice() == CONTINUE){
         delay() }

    home()
     */

}