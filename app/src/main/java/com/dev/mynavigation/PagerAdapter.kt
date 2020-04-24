package com.dev.mynavigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class PagerAdapter(private val context : Context) : Adapter<PagerAdapter.PagerViewHolder>() {

    private val data = listOf("Message page", "Contact Page", "Find Page", "Profile Page")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(LayoutInflater.from(context).inflate(R.layout.pager_item, parent, false))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) =
        holder.onBind(data[position])

    override fun getItemCount(): Int = data.size

    inner class PagerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val textView = itemView.findViewById<TextView>(R.id.pager_item_text)

        fun onBind(content : String) {
            textView.text = content
        }
    }
}