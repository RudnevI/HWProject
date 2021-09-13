package com.example.hwproject.lesson_11


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.repository.Repository
import com.example.hwproject.R
import com.example.hwproject.databinding.ActivityDaggerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DaggerActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: Repository

    private lateinit var binding: ActivityDaggerBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDaggerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = repository.getUser()

        val userId = user.id
        val userName = user.name

        binding.userId.text = userId.toString()
        binding.userName.text =  userName

        /*  DaggerRepositoryComponent
              .builder()
              .build()
              .injectIntoActivity(this)*/


    }
}