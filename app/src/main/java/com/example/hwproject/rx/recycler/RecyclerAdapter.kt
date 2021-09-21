package com.example.hwproject.rx.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hwproject.databinding.ActivityUserRecyclerBinding
import com.example.hwproject.rx.model.User

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.UserViewHolder>() {

    private var data: ArrayList<User> = arrayListOf()

    fun setData(list: ArrayList<User>) {
        data = list
    }

    inner class UserViewHolder(private val binding: ActivityUserRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun setUser(user: User) {
                binding.userName.text = user.name
                binding.email.text = user.email
                binding.gender.text = user.gender.name

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityUserRecyclerBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = data[position]
        holder.setUser(user)

    }

    override fun getItemCount(): Int {
        return data.size
    }


}