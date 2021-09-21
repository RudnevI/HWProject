package com.example.hwproject.lesson_13

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object UserFlow {

    val float = Pager(PagingConfig(pageSize = 10)) {
        UsersPagingSource(throw Exception())

    }.flow.cachedIn(
        CoroutineScope(Dispatchers.IO)
    )
}