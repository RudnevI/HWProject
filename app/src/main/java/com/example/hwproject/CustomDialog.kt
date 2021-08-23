package com.example.hwproject

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatButton

class CustomDialog(context: Context): Dialog(context), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog)
        val confirmationButton = findViewById<AppCompatButton>(R.id.dialog_confirm_button)
        confirmationButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when(v.id) {
            R.id.dialog_confirm_button -> dismiss()
            else -> {

            }
        }
        dismiss()
    }
}