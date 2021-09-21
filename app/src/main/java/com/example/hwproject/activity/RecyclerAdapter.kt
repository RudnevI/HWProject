package com.example.hwproject.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hwproject.R

class RecyclerAdapter(private val data: ArrayList<Item>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val textView: TextView = view.findViewById(R.id.recycler_title)
        private  val imageView: ImageView = view.findViewById(R.id.recycler_image)

        fun setData(item: Item) {
            textView.text = item.title
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, item.imageId))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Item(
         val title: String,
         val imageId: Int
    )

}