package com.biehnary.friction.domain.config

data class SessionLimitConfig(
    val enabled: Boolean,
    val intervalMinutes: Int
)
