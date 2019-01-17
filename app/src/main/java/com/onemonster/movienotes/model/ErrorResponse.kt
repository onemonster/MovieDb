package com.onemonster.movienotes.model

import com.squareup.moshi.Json

data class ErrorResponse(
    @get:Json(name = "status_code") val statusCode: Int = 0,
    @get:Json(name = "status_message") val statusMessage: String = "",
    @get:Json(name = "success") val success: Boolean = false
)