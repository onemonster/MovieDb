package com.onemonster.movienotes.repository

import androidx.room.EmptyResultSetException
import com.onemonster.movienotes.model.PagedResource
import com.onemonster.movienotes.model.toErrorResponse
import io.reactivex.Observable
import retrofit2.HttpException
import kotlin.concurrent.thread

abstract class NetworkBoundResource<ResultType, ResponseType> {
    private var result: Observable<PagedResource<ResultType>>

    init {
        result = Observable.create { emitter ->
            var data: ResultType? = null
            emitter.onNext(PagedResource.loading(data))

            loadFromDb().subscribe {
                emitter.onNext(PagedResource.success(it))
            }

            fetchFromService()
                .subscribe({
                    mapResponseTypeToResultType(it).let { result ->
                        data = result
                        thread {
                            storeInDb(result)
                        }
                        emitter.onNext(
                            PagedResource.success(
                                result,
                                mapResponseTypeToIsLastPage(it)
                            )
                        )
                        emitter.onComplete()
                    }
                }) { t ->
                    (t as? HttpException)?.toErrorResponse()?.let { error ->
                        emitter.onNext(PagedResource.error(error, data))
                    }
                    emitter.onComplete()
                }
        }
    }

    fun asObservable(): Observable<PagedResource<ResultType>> = result

    protected open fun loadFromDb(): Observable<ResultType> = Observable.error(EmptyResultSetException("Default"))

    protected abstract fun fetchFromService(): Observable<ResponseType>

    protected abstract fun mapResponseTypeToResultType(responseType: ResponseType): ResultType

    protected abstract fun mapResponseTypeToIsLastPage(responseType: ResponseType): Boolean

    protected abstract fun storeInDb(data: ResultType)

    protected abstract fun shouldStoreResult(): Boolean
}