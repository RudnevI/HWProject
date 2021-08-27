package com.example.hwproject.activity


import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler

import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


import com.example.hwproject.R
import com.example.hwproject.service.TestForegroundService
import java.lang.Exception


class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.service_layout)

        findViewById<Button>(R.id.launchForegroundService).setOnClickListener {
            val intent = Intent(this, TestForegroundService::class.java)
            startService(intent)
            updateStatus()
        }

        findViewById<Button>(R.id.stopForegroundService).setOnClickListener {
            val intent = Intent(this, TestForegroundService::class.java)
            intent.action = TestForegroundService.ACTION_STOP

            startService(intent)
            Handler().postDelayed({updateStatus()}, 100)
        }

    }

    private fun updateStatus() {
        if (isServiceRunning(TestForegroundService::class.java)) {
            findViewById<TextView>(R.id.serviceStatus).text= "Service is running"
        }
        else {
            findViewById<TextView>(R.id.serviceStatus).text= "Service is not running"
        }
    }

    @SuppressWarnings("deprecation")
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        try {
            val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

            for (service in manager.getRunningServices(Int.MAX_VALUE)) {
                if(service.service.className == serviceClass.name) {
                    return true
                }
            }
        } catch (exception: Exception) {
            return false
        }
        return  false
    }



}