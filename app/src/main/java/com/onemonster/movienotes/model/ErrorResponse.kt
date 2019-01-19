package com.onemonster.movienotes.model

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException

data class ErrorResponse(
    @field:Json(name = "status_code") val statusCode: Int = 0,
    @field:Json(name = "status_message") val statusMessage: String = "",
    @field:Json(name = "success") val success: Boolean = false
)

fun HttpException.toErrorResponse(): ErrorResponse? {
    return this.response().errorBody()?.string()?.let {
        try {
            Moshi.Builder().build().adapter(ErrorResponse::class.java).fromJson(it)
        } catch (e: IOException) {
            ErrorResponse(this.code(), this.localizedMessage)
        }
    }
}