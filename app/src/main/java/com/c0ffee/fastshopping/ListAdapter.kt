package com.c0ffee.fastshopping

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_view.view.*

class ListAdapter(private val lists: List<ShoppingList>): RecyclerView.Adapter<ListAdapter.ListHolder>() {

    class ListHolder(private val listView: ListView): RecyclerView.ViewHolder(listView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val listView = ListView(parent.context)
        return ListHolder(listView)
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.itemView.list_title_text.text = lists[position].title
    }
}