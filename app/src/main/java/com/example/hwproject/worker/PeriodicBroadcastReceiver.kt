package com.example.hwproject.worker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PeriodicBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(
            context,
            "Hello from periodic broadcast receiver",
            Toast.LENGTH_LONG
        )
            .show()
    }
}