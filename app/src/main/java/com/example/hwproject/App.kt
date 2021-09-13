package com.example.hwproject

import android.app.Application
import com.example.hwproject.lesson_10.module.paymentsModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        /*startKoin {
            androidLogger()
            androidContext(this@App)
            modules(paymentsModule)



        }*/


    }
}