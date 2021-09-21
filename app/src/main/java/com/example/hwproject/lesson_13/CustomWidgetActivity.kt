package com.example.hwproject.lesson_13


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hwproject.R
import com.example.hwproject.lesson_13.widgets.FeedbackWidget

class CustomWidgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_widget)

        findViewById<FeedbackWidget>(R.id.feedback_widget).onEndIconClick {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }
}