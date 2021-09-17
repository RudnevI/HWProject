package com.example.hwproject

import android.app.Application
import com.example.hwproject.lesson_12.room.Repository


class App: Application() {

    override fun onCreate() {
        super.onCreate()
        /*startKoin {
            androidLogger()
            androidContext(this@App)
            modules(paymentsModule)



        }*/

        Repository.initialize(this)


    }
}