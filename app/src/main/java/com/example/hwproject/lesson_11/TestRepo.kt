package com.example.hwproject.lesson_11

import dagger.Provides
import javax.inject.Inject

class TestRepo @Inject constructor() {



    companion object {

        private val INSTANCE : TestRepo? = null

        @Provides
        fun getRepo(): TestRepo {
            return INSTANCE ?: TestRepo()
        }
    }
}