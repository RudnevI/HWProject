package com.example.hwproject

import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.hwproject.lesson_12.room.Repository
import com.example.hwproject.worker.LogWorker
import java.util.concurrent.TimeUnit


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        /*startKoin {
            androidLogger()
            androidContext(this@App)
            modules(paymentsModule)



        }*/

        Repository.initialize(this)
        WorkManager
            .getInstance(applicationContext)
            .enqueue(
                PeriodicWorkRequestBuilder<LogWorker>(
                    PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                    .build()
            )


    }
}