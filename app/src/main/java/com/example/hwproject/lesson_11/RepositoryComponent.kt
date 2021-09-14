package com.example.hwproject.lesson_11


import dagger.Component
import dagger.Provides
import dagger.hilt.DefineComponent


@Component(modules = [RepositoryModule:: class])
interface RepositoryComponent {

    @Provides
    fun injectIntoActivity(activity: DaggerActivity)
}