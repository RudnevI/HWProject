package com.example.hwproject.service

import android.app.Service
import android.content.Intent

import android.os.IBinder
import com.example.hwproject.BuildConfig


class TestForegroundService : Service() {

    companion object {
        const val ACTION_STOP = "${BuildConfig.APPLICATION_ID}.stop"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val action = intent?.action
        if (action != null && action == ACTION_STOP) {
            stopSelf()
        }
        return START_STICKY
    }

}