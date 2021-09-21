package com.example.hwproject.activity


import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hwproject.R
import com.example.hwproject.broadcastreceiver.TestBroadcastReceiver

class BroadcastActivity : AppCompatActivity() {

    private val receiver = TestBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }

        findViewById<Button>(R.id.sendIntentButton).setOnClickListener{
            val intent = Intent()
            intent.setAction("com.example.hwproject.broadcastreceiver.TestBroadcastReceiver.NOTIFICATION")
            intent.putExtra("data", "send data")
            sendBroadcast(intent)
        }
        val filter = IntentFilter("com.example.hwproject.broadcastreceiver.TestBroadcastReceiver.NOTIFICATION")
        registerReceiver(receiver, filter)


    }



    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}