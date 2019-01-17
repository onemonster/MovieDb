package com.onemonster.movienotes.network

import com.onemonster.movienotes.model.PagedResponse
import com.onemonster.movienotes.model.entity.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun fetchDiscoverMovie(@Query("page") page: Int): Observable<PagedResponse<Movie>>
}