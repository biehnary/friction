package com.biehnary.friction.runtime
import kotlinx.coroutines.Job

data class ActiveFriction (
    val packageName: String,
    val job: Job
)