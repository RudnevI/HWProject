package com.example.hwproject.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.hwproject.R

class PdfViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        attachEventListenerToOpenPdfButton()

    }

    private fun getFileUri(): Uri {

        return Uri.parse("http://www.africau.edu/images/default/sample.pdf")



    }
    private fun attachEventListenerToOpenPdfButton() {
        val button = findViewById<AppCompatButton>(R.id.openPdfButton)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, getFileUri())
            startActivity(intent)
        }
    }

}