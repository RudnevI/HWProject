package com.example.hwproject.lesson_8


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hwproject.R
import com.example.hwproject.databinding.ActivityCoroutineBinding
import com.example.hwproject.rx.recycler.RecyclerAdapter
import kotlinx.coroutines.*
import java.lang.Exception

class CoroutineActivity : AppCompatActivity() {

    private lateinit var job: Job
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)
    private lateinit var binding: ActivityCoroutineBinding
    private val adapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadUsers()
    }

    private fun loadUsers() {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = CoroutineRetrofitClient.getUsers()
                withContext(Dispatchers.Main) {

                    if (response.data.isNotEmpty()) {
                        adapter.setData(response.data)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}