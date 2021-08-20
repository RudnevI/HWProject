package com.example.hwproject.service

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


interface NavigationService {

    fun goToActivity(contextActivity: AppCompatActivity, destinationActivity: AppCompatActivity, id: Int)


}