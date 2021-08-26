package com.example.hwproject.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TestBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        StringBuilder().apply {
            append("#A Action: ${intent?.action}\n")
            append("#A Data: ${intent?.data}")
            append("#A Data: ${intent?.extras}")
            toString().also {
                data -> Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
            }
        }

    }
}