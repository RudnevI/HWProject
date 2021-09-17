package com.example.hwproject.lesson_12

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hwproject.R

class PreferenceActivity : AppCompatActivity() {

    companion object {
        const val PREFERENCES = "PreferenceActivityPreferences"
        const val COUNTER = "counter"
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        setViews()
    }

    private fun setViews() {
        val counter = findViewById<TextView>(R.id.counter)
        val increment = findViewById<Button>(R.id.increment)

        val preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE)

        val preferencesEditor = preferences.edit()

        val count = preferences.getInt(COUNTER, 0)

        counter.text = count.toString()
        increment.setOnClickListener {
            preferencesEditor.putInt(COUNTER, count + 1)
            preferencesEditor.apply()
        }
    }
}