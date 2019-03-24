package com.c0ffee.fastshopping

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_view.view.*

class ListAdapter(private val lists: List<ShoppingList>): RecyclerView.Adapter<ListAdapter.ListHolder>() {
    private var currentLists: MutableList<ShoppingList> = ArrayList(lists)

    class ListHolder(private val listView: ListView): RecyclerView.ViewHolder(listView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val listView = ListView(parent.context)
        return ListHolder(listView)
    }

    fun filter(text: String) {
        currentLists.clear()
        if (text.isEmpty()) {
            currentLists.addAll(lists)
        } else {
            val matchTitle = text.toLowerCase()
            currentLists = lists.filter {
                it.title.toLowerCase().contains(matchTitle)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = currentLists.size

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.itemView.list_title_text.text = currentLists[position].title
    }
}