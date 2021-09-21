package com.example.hwproject.service

import android.app.Service
import android.content.Intent
import android.os.*

class TestService : Service() {

    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHelper? = null

    private inner class ServiceHelper(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            try {
                Thread.sleep(5000)
            } catch (e: Exception) {
                Thread.currentThread().interrupt()
            }
            stopSelf(msg.arg1)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        HandlerThread("Arguments").apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHelper(looper)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("#A service started")
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }
        return START_STICKY
    }

    override fun onDestroy() {

        println("#A service destroyed")
        super.onDestroy()
    }
}