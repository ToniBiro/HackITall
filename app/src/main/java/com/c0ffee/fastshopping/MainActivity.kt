package com.c0ffee.fastshopping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var PRODUCT_DB: ProductDatabaseHelper
        lateinit var LIST_DB: ListDatabaseHelper
        val LISTS: ArrayList<ShoppingList> = ArrayList()
        const val MESSAGE_LIST_ID = "com.c0ffee.fastshopping.ceva"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PRODUCT_DB = ProductDatabaseHelper(this)
        LIST_DB = ListDatabaseHelper(this)

        val toolbar = main_toolbar
        setSupportActionBar(toolbar)

        list_recycler_view.layoutManager = LinearLayoutManager(this)
        list_recycler_view.adapter = ListAdapter(LISTS)
    }

    fun openList(view: View) {
        val intent = Intent(this, ShoppingListActivity::class.java)
        val index = list_recycler_view.findContainingViewHolder(view)!!.adapterPosition
        intent.putExtra(MESSAGE_LIST_ID, index)
        startActivity(intent)
    }

    fun createList(view: View) {
        LISTS.add(ShoppingList("Lista #${LISTS.size + 1}"))
        list_recycler_view.adapter!!.notifyItemChanged(LISTS.size - 1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }
}
