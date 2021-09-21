package com.example.hwproject.lesson_8


import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun
import com.example.hwproject.R
import com.example.hwproject.databinding.ActivityCoroutineBinding
import com.example.hwproject.rx.recycler.RecyclerAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.lang.Exception

class CoroutineActivity : AppCompatActivity() {

    private lateinit var job: Job
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)
    private lateinit var binding: ActivityCoroutineBinding
    private val adapter = RecyclerAdapter()
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = binding.progressBar

        loadUsers()
    }

    private fun loadUsers() {
        val client = CoroutineRetrofitClient()

        binding.progressBar.visibility = View.VISIBLE
        job = CoroutineScope(Dispatchers.Main).launch {

            client.getUsers().onStart {
                binding.progressBar.visibility = View.VISIBLE
            }.catch { exception -> Toast.makeText(this@CoroutineActivity, exception.message, Toast.LENGTH_LONG).show() }
                .onCompletion {
                    binding.progressBar.visibility = View.GONE
                }.collect {
                    withContext(Dispatchers.Main) {
                        println("#CoroutineActivity: ${Looper.myLooper() == Looper.getMainLooper()}")
                        adapter.setData(it.data)
                    }
                }


        }
    }


}