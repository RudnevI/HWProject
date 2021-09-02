package com.example.hwproject.rx.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hwproject.databinding.ActivityUserRecyclerBinding
import com.example.hwproject.databinding.CommentsRecyclerViewBinding
import com.example.hwproject.databinding.RxRecyclerViewBinding
import com.example.hwproject.rx.model.Comment
import com.example.hwproject.rx.model.User

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    private var data: ArrayList<Comment> = arrayListOf()

    fun setData(list: ArrayList<Comment>) {
        data = list
    }

    inner class CommentViewHolder(private val binding: CommentsRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setComment(comment: Comment) {
           binding.body.text = comment.body

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentsRecyclerViewBinding.inflate(inflater, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = data[position]
        holder.setComment(comment)

    }

    override fun getItemCount(): Int {
        return data.size
    }


}