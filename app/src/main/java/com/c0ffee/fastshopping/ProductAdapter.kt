package com.c0ffee.fastshopping

import android.content.Context
import android.graphics.drawable.Icon
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_view.view.*
import kotlinx.android.synthetic.main.product_view.view.*

class ProductAdapter(private val activity: ShoppingListActivity,
                     private val productList: List<String>): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    class ProductHolder(private val productView: ProductView): RecyclerView.ViewHolder(productView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val productView = ProductView(parent.context)
        return ProductHolder(productView)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.itemView.product_name.text = productList[position]

        val chkbox: Int
        if (MainActivity.PRODUCT_DB.testProductInShop(productList[position], activity.currentShop)) {
            chkbox = android.R.drawable.checkbox_on_background
            val price = MainActivity.PRODUCT_DB.getPriceProductInShop(productList[position], activity.currentShop)
            holder.itemView.product_quantity.text = price.toString() + " Lei"
        } else {
            chkbox = android.R.drawable.checkbox_off_background
            holder.itemView.product_quantity.text = "Indisponibil"
        }

        holder.itemView.product_availability.setImageResource(chkbox)
    }
}