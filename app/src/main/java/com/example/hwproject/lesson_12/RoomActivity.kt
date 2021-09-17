package com.example.hwproject.lesson_12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hwproject.databinding.ActivityRoomBinding
import com.example.hwproject.lesson_12.room.Repository
import com.example.hwproject.lesson_12.room.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private var adapter = UserRecyclerViewAdapter()

    private val userDatabase by lazy {
        Repository.database
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViews()

    }


    private fun setViews() {
        binding.apply {
            recycler.layoutManager = LinearLayoutManager(this@RoomActivity)
            recycler.adapter = adapter

            CoroutineScope(Dispatchers.IO).launch {
                userDatabase.userDao().getAllUser().collect {
                    withContext(Dispatchers.Main) {
                        adapter.setData(it)
                    }
                }
            }
            signUpButton.setOnClickListener {
                val user = User(
                    firstName = firstName.text.toString(),
                    secondName = lastName.text.toString(),
                    id = 0
                )



                CoroutineScope(Dispatchers.IO).launch {
                    userDatabase.userDao().addUser(user)

                }

            }

            removeButton.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {
                    userDatabase.userDao().deleteByNameAndLastName(
                        firstName.text.toString(),
                        lastName.text.toString()
                    )
                }
            }
        }
    }
}