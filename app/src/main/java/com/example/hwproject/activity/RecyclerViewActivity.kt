package com.example.hwproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hwproject.R
import com.example.hwproject.activity.RecyclerAdapter.Item

class RecyclerViewActivity : AppCompatActivity() {

    val recyclerList: ArrayList<Item> = arrayListOf(
        Item("Item 1", R.drawable.ic_mono_mirror),
        Item("Item 2", R.drawable.ic_png)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerAdapter(recyclerList)
        recyclerView.adapter = adapter
    }


}

