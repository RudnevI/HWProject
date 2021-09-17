package com.example.hwproject.lesson_13


import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.hwproject.R
import java.lang.IllegalArgumentException

class ViewPagerActivity : FragmentActivity() {

    private lateinit var viewPager2: ViewPager2

    private val adapter = FragmentAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        viewPager2 = findViewById<LinearLayout>(R.id.container).getChildAt(0) as ViewPager2
        viewPager2.adapter = adapter

    }

    companion object {
        private const val FRAGMENT_COUNT = 2
    }

    private inner class FragmentAdapter(activity: FragmentActivity) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return FRAGMENT_COUNT
        }

        override fun createFragment(position: Int): Fragment =
            when (position) {
                0 -> TransfersFragment()
                1 -> HistoryFragment()
                else -> throw IllegalArgumentException("Not a valid parameter number!")
            }


    }


}