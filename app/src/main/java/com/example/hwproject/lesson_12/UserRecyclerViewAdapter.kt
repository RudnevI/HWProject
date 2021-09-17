package com.example.hwproject.lesson_12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hwproject.databinding.RecyclerItemUserBinding
import com.example.hwproject.lesson_12.room.User

class UserRecyclerViewAdapter : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>(){
    private var data: List<User> = listOf()

    fun setData(list: List<User>) {
        data = list
        notifyDataSetChanged()
    }

    class UserViewHolder(private val binding: RecyclerItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(user: User) {
            binding.firstname.text = user.firstName
            binding.lastname.text = user.secondName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = RecyclerItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}