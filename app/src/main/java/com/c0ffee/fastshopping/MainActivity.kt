package com.c0ffee.fastshopping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    companion object {
        lateinit var PRODUCT_DB: ProductDatabaseHelper
        lateinit var LIST_DB: ListDatabaseHelper
        var LISTS: ArrayList<ShoppingList> = ArrayList()
        const val MESSAGE_LIST_ID = "com.c0ffee.fastshopping.ceva"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PRODUCT_DB = ProductDatabaseHelper(this)
        LIST_DB = ListDatabaseHelper(this)
        LISTS = LIST_DB.read()
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
        LIST_DB.onUpdate(LISTS)

        val listAdapter = list_recycler_view.adapter!! as ListAdapter
        listAdapter.filter("")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)

        val searchMenuItem = menu!!.findItem(R.id.action_search)
        val searchView = searchMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        val listAdapter = list_recycler_view.adapter!! as ListAdapter
        listAdapter.filter(query)

        return false
    }

    override fun onQueryTextSubmit(query: String): Boolean = false
}
