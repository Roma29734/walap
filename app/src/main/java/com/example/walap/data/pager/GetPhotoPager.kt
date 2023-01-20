package com.example.walap.data.pager

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.walap.data.model.PhotoModel
import com.example.walap.data.model.photoModelItem.PhotoModelItem
import com.example.walap.data.repository.WallpaperRepository
import com.example.walap.utils.ResultRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class GetPhotoPager(
    private val repository: WallpaperRepository,
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
            repository.getTopPhotoPro(page)
                .flatMap { result ->
                    when (result) {

                        is ResultRepo.Error -> {
                            Log.d("checkIthernetConnect", "обработал ошибку")
                            Log.d(
                                "checkBagInIthernetConnect",
                                "Пришла ошбика, обробатываю pagerGet"
                            )
                            Single.just(result.message?.let { LoadResult.Error(it) }!!)
                        }
                        is ResultRepo.Success -> {
                            Log.d(
                                "checkBagInIthernetConnect",
                                "Пришли отличные данные, обробатываю pagerGet"
                            )
                            Single.just(result.data!!.let {
                                LoadResult.Page(
                                    it,
                                    prevKey = if (page == 1) null else page - 1,
                                    nextKey = if (it.isEmpty()) null else page + 1,
                                )
                            })
                        }
                    }
                }

//            responce.map {
//                LoadResult.Page(
//                    it,
//                    prevKey = if (page == 1) null else page - 1,
//                    nextKey = if(it.isEmpty()) null else page +1,
//                )
//            }
        } catch (e: Exception) {
            Log.d("checkBagInIthernetConnect", "Словил ошибку pagerGet")
            Log.d("aboaExpection", "Сработал catch")
            Single.just(LoadResult.Error(e))
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
}