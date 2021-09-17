package com.example.hwproject.lesson_13

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hwproject.rx.model.User

interface UserStore {

    fun getUsers(pageNumber: Int): List<User>

    fun nextPageIndex(): Int

}

class UsersPagingSource(private val userStore: UserStore) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val nextPageNumber = params.key ?: 1
        val response = userStore.getUsers(nextPageNumber)
        return LoadResult.Page(
            data = response,
            prevKey = null,
            nextKey = userStore.nextPageIndex()
        )

    }


}