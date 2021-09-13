package com.example.hwproject.lesson_11


import com.example.domain.dagger.RepositoryModule
import dagger.Component

@Component(modules = [RepositoryModule:: class])
interface RepositoryComponent {

    fun injectIntoActivity(activity: DaggerActivity)
}