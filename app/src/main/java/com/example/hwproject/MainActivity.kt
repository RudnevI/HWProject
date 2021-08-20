package com.example.hwproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.example.hwproject.implementation.NavigationServiceImplementation
import com.example.hwproject.service.NavigationService

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToSignUpButton = findViewById<AppCompatButton>(R.id.goToSignUpButton)
        goToSignUpButton.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        /*navigationService.goToActivity(this, SignUpActivity, R.id.goToSignUpButton)*/
        val goToPdfViewerButton = findViewById<AppCompatButton>(R.id.goToPdfButton)
        goToPdfViewerButton.setOnClickListener{
            val intent = Intent(this, PdfViewerActivity::class.java)
            startActivity(intent)
        }

        val goToRecycleViewButton= findViewById<AppCompatButton>(R.id.goToRecycleView)
        goToRecycleViewButton.setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

    }


}