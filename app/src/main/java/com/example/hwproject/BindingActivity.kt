package com.example.hwproject


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.hwproject.databinding.ActivityBindingBinding

class BindingActivity : AppCompatActivity() {

    private  var binding: ActivityBindingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBindingBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.textForBinding()
    }

}