package com.onemonster.movienotes.model

class PagedResource<T>(
    val status: Status,
    val data: T?,
    val error: ErrorResponse?,
    val isLastPage: Boolean
) {
    override fun toString(): String {
        return when (this.status) {
            Status.SUCCESS -> "SUCCESS: $data"
            Status.ERROR -> "ERROR: $error / DATA cached: $data"
            Status.LOADING -> "LOADING: $data"
        }
    }

    companion object {
        fun <T> success(data: T, isLastPage: Boolean = false) = PagedResource(Status.SUCCESS, data, null, isLastPage)
        fun <T> error(error: ErrorResponse?, data: T? = null) = PagedResource(Status.ERROR, data, error, false)
        fun <T> loading(data: T? = null) = PagedResource(Status.LOADING, data, null, false)
    }
}