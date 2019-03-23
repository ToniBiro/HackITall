package com.c0ffee.fastshopping

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListAdapter(private val lists: List<ShoppingList>): RecyclerView.Adapter<ListAdapter.ListHolder>() {

    class ListHolder(val listView: CardView): RecyclerView.ViewHolder(listView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val listView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_view, parent, false) as CardView
        return ListHolder(listView)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {

    }
}