package com.example.slave

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NumberBookListAdapter(): RecyclerView.Adapter<NumberBookListAdapter.NumberBookListViewHolder>() {
    var listData = mutableListOf<NumberBook>()
    var helper:SqliteHelper? = null
    override fun getItemCount(): Int {
        return listData.count()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberBookListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.number_book_item, parent, false)
        return NumberBookListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberBookListViewHolder, position: Int) {
        val numberBook: NumberBook=listData.get(position)
        holder.name.text = numberBook.name.toString()
        holder.number.text = numberBook.number.toString()
    }
    inner class NumberBookListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var number: TextView = view.findViewById(R.id.number)
    }
}