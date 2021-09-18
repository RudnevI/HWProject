package com.example.hwproject.homework_bound_service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

class BoundService : Service() {

    private val binder = BoundBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }


    fun displayToast(context: Context) {
        Toast.makeText(
            context,
            "Hooray, BoundService is working!",
            Toast.LENGTH_LONG
        )
            .show()
    }

    inner class BoundBinder : Binder() {

        fun getService(): BoundService = this@BoundService

    }
}