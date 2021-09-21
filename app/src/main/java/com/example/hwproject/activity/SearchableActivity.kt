package com.example.hwproject.activity

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.hwproject.R

import com.example.hwproject.databinding.ActivitySearchableBinding
import java.util.*

class SearchableActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchableBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchableBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbarWithSearch)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        setContentView(binding.root)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.search.apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
        }

        return true

    }

}