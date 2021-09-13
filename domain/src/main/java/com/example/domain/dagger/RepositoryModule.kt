package com.example.domain.dagger

import com.example.domain.datasource.DataSource
import com.example.domain.datasource.impl.RemoteDataSourceImpl
import com.example.domain.datasource.remote.Api
import com.example.domain.datasource.remote.MockApi
import com.example.domain.repository.Repository
import com.example.domain.repository.impl.RepositoryImpl
import dagger.Module
import dagger.Provides


@Module
class RepositoryModule {

    @Provides
    fun getApi(): Api = MockApi()

    @Provides
    fun getDataSource() : DataSource = RemoteDataSourceImpl(getApi())

    @Provides
    fun getRepository(): Repository = RepositoryImpl(getDataSource())
}