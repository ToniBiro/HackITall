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
    private val lists: ArrayList<ShoppingList> = arrayListOf(ShoppingList("Mancare"), ShoppingList("O lista"))

    companion object {
        lateinit var DB: DatabaseHelper
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DB = DatabaseHelper(this)

        setSupportActionBar(main_toolbar)

        list_recycler_view.layoutManager = LinearLayoutManager(this)
        list_recycler_view.adapter = ListAdapter(lists)
    }

    fun openList(view: View) {
        val intent = Intent(this, ShoppingListActivity::class.java)
        startActivity(intent)
    }

    fun createList(view: View) {
        val listAdapter = list_recycler_view.adapter!! as ListAdapter
        lists.add(ShoppingList("Lista #${lists.size + 1}"))
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
