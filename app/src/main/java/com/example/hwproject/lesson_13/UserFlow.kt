package com.example.hwproject.lesson_13

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope

object UserFlow {

    val float = Pager(PagingConfig(pageSize = 10)) {
        UsersPagingSource(throw Exception())

    }.flow.cachedIn(
        CoroutineScope()
    )
}