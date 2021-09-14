package com.example.hwproject.lesson_11

import android.app.Activity
import com.example.domain.datasource.DataSource
import com.example.domain.datasource.impl.RemoteDataSourceImpl
import com.example.domain.datasource.remote.Api
import com.example.domain.datasource.remote.MockApi
import com.example.domain.repository.Repository
import com.example.domain.repository.impl.RepositoryImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

   /* @Provides
    fun getApi(): Api = MockApi()

    @Provides
    fun getDataSource() : DataSource = RemoteDataSourceImpl(getApi())

    @Provides
    fun getRepository(): Repository = RepositoryImpl(getDataSource())*/

    @Singleton
    @Provides
    fun getRepo() : TestRepo = TestRepo.getRepo()
}