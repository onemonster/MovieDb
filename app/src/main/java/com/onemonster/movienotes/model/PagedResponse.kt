package com.onemonster.movienotes.model

import com.squareup.moshi.Json

data class PagedResponse<E>(
    @field:Json(name = "page") val page: Int = 0,
    @field:Json(name = "total_results") val totalResults: Int = 0,
    @field:Json(name = "total_pages") val totalPages: Int = 0,
    @field:Json(name = "results") val results: List<E> = listOf()
) {
    val isLast: Boolean get() = page >= totalPages
}