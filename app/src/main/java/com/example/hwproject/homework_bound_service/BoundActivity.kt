package com.example.hwproject.homework_bound_service

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.widget.AppCompatButton
import com.example.hwproject.R

class BoundActivity : Activity() {

    private lateinit var mService: BoundService

    private var mBound: Boolean = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as BoundService.BoundBinder
            mService = binder.getService()
            mBound = true

        }

        override fun onServiceDisconnected(name: ComponentName) {
            mBound = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound)
    }

    override fun onStart() {
        super.onStart()

        /* Intent(this, BoundService::class.java).also {
             intent -> bindService(intent, connection, Context.BIND_AUTO_CREATE)
         }*/

        val intent = Intent(this, BoundService::class.java)


        bindService(intent, connection, Context.BIND_AUTO_CREATE)


        findViewById<AppCompatButton>(R.id.button_display_message_bound_service).setOnClickListener {
            if (mBound) {
                mService.displayToast(this)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            unbindService(connection)
            mBound = false
        }
    }


}