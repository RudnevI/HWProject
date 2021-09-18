package com.example.hwproject.worker

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hwproject.R

class PeriodicBroadcastActivity : AppCompatActivity() {


    private val periodicBroadcastReceiver = PeriodicBroadcastReceiver()

    private val filter = IntentFilter("periodic_broadcast")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_periodic_broadcast)

        registerReceiver(periodicBroadcastReceiver, filter)

    }
}