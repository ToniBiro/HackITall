package com.c0ffee.fastshopping

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_view.view.*
import kotlinx.android.synthetic.main.product_view.view.*

class ProductAdapter(private val productList: List<String>): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    class ProductHolder(private val productView: ProductView): RecyclerView.ViewHolder(productView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val productView = ProductView(parent.context)
        return ProductHolder(productView)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.itemView.product_name.text = productList[position]
    }
}