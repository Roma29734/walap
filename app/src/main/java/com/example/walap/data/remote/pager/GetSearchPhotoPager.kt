package com.example.walap.data.remote.pager

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.walap.data.model.SearchModel
import com.example.walap.data.model.photoModelItem.PhotoModelItem
import com.example.walap.data.repository.WallpaperRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

class GetSearchPhotoPager(
    private val repository: WallpaperRepository,
    private val query: String,
) : RxPagingSource<Int, PhotoModelItem>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoModelItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, PhotoModelItem>> {
        val page = params.key ?: 1

        return try {
            repository.getSearchPhoto(query = query, page = page)
                .subscribeOn(Schedulers.io())
                .map {
                    toLoadResult(it, page)
                }.onErrorReturn {
                    LoadResult.Error(it)
                }
        } catch (exception: IOException) {
            Single.just(LoadResult.Error(exception))
        } catch (exception: HttpException) {
            Single.just(LoadResult.Error(exception))
        } catch (exception: InvalidObjectException) {
            Single.just(LoadResult.Error(exception))
        } catch (exception: Exception) {
            Single.just(LoadResult.Error(exception))
        }

    }

    private fun toLoadResult(data: SearchModel, position: Int): LoadResult<Int, PhotoModelItem> {
        return LoadResult.Page(
            data = data.results,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (data.results.isEmpty()) null else position + 1
        )
    }
}