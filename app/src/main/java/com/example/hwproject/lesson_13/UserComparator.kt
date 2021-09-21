package com.example.hwproject.lesson_13

import androidx.recyclerview.widget.DiffUtil
import com.example.hwproject.rx.model.User

object UserComparator: DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        TODO("Not yet implemented")
    }
}