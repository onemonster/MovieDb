package com.onemonster.movienotes.repository

import com.onemonster.movienotes.model.PagedResource
import com.onemonster.movienotes.model.PagedResponse
import com.onemonster.movienotes.model.entity.Movie
import com.onemonster.movienotes.network.MovieService
import com.onemonster.movienotes.room.MovieDao
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService
) {
    fun getMovies(page: Int, storeResult: Boolean): Observable<PagedResource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, PagedResponse<Movie>>() {

            override fun loadFromDb(): Observable<List<Movie>> =
                movieDao.getMovieList(page)
                    .toObservable()
                    .doOnNext { Timber.d("Dispatching ${it.size} users from DB...") }

            override fun fetchFromService(): Observable<PagedResponse<Movie>> =
                movieService.fetchDiscoverMovie(page).doOnNext { Timber.d("__ FETCH FROM SERVICE $it") }

            override fun mapResponseTypeToResultType(responseType: PagedResponse<Movie>): List<Movie> =
                responseType.results

            override fun mapResponseTypeToIsLastPage(responseType: PagedResponse<Movie>): Boolean =
                responseType.isLast

            override fun storeInDb(data: List<Movie>) = movieDao.insert(data)

            override fun shouldStoreResult(): Boolean = storeResult
        }.asObservable()
}