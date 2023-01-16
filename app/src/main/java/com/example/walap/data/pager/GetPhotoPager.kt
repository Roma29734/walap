package com.example.walap.data.pager

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.walap.data.model.photoModelItem.PhotoModelItem
import com.example.walap.data.repository.WallpaperRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetPhotoPager(
    private val repository: WallpaperRepository,
): RxPagingSource<Int, PhotoModelItem>(){

    override fun getRefreshKey(state: PagingState<Int, PhotoModelItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, PhotoModelItem>> {
        val page = params.key ?: 1

        return try {
            val responce = repository.getTopPhoto(page)
                .subscribeOn(Schedulers.io())
            responce.map {
                LoadResult.Page(
                    it,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if(it.isEmpty()) null else page +1,
                )
            }
        }catch (e: Exception) {
            Single.just(LoadResult.Error(e))
        }

    }
}

//
//class GithubPagingSource(
//    private val api: NetworkApi,
//    private val userName: String
//) : PagingSource<Int, Repository>() {
//
//    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
//        return try {
//            val page = params.key ?: FIRST_PAGE
//            val response = api.fetchRepos(userName, page, params.loadSize)
//            LoadResult.Page(
//                data = response,
//                prevKey = if (page == FIRST_PAGE) null else page - 1,
//                nextKey = if (response.isEmpty()) null else page + 1
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}