package com.onemonster.movienotes.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onemonster.movienotes.model.PagedResource
import com.onemonster.movienotes.model.entity.Movie
import com.onemonster.movienotes.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val movieDiscoveryPagedResource: LiveData<PagedResource<List<Movie>>>
        get() = _movieDiscoveryResource
    private val _movieDiscoveryResource = MutableLiveData<PagedResource<List<Movie>>>()

    fun fetchMovieDiscovery(page: Int) {
        val disposable = movieRepository.getMovies(page, true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _movieDiscoveryResource.value = it }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}