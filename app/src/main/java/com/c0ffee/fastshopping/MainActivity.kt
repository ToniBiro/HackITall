package com.c0ffee.fastshopping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val lists: ArrayList<ShoppingList> = arrayListOf(ShoppingList("Mancare"), ShoppingList("O lista"))

    companion object {
        lateinit var PRODUCT_DB: ProductDatabaseHelper
        lateinit var LIST_DB: ListDatabaseHelper
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PRODUCT_DB = ProductDatabaseHelper(this)
        LIST_DB = ListDatabaseHelper(this)

        val toolbar = main_toolbar
        setSupportActionBar(toolbar)

        list_recycler_view.layoutManager = LinearLayoutManager(this)
        list_recycler_view.adapter = ListAdapter(lists)
    }

    fun openList(view: View) {
        val intent = Intent(this, ShoppingListActivity::class.java)
        startActivity(intent)
    }

    fun createList(view: View) {
        lists.add(ShoppingList("Lista #${lists.size + 1}"))
        list_recycler_view.adapter!!.notifyItemChanged(lists.size - 1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }
}
