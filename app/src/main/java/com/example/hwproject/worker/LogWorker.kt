package com.example.hwproject.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


private const val TAG = "LogWorker"

class LogWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        return try {
            Log.d(TAG, "Hello from LogWorker")
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Something went wrong, to be precise: ${e.printStackTrace()}")
            Result.failure()
        }


    }
}