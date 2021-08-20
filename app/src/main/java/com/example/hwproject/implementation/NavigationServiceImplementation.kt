package com.example.hwproject.implementation

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.hwproject.service.NavigationService

class NavigationServiceImplementation : NavigationService {
    override fun goToActivity(
        contextActivity: AppCompatActivity,
        destinationActivity: AppCompatActivity,
        id: Int
    ) {
        val navigationView = contextActivity.findViewById<View>(id)
        navigationView.setOnClickListener{
            val intent = Intent(contextActivity, destinationActivity::class.java)
            contextActivity.startActivity(intent)
        }
    }
    // TODO: 19.08.2021 write working implementation 

}