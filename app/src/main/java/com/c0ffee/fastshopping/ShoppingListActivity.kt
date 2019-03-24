package com.c0ffee.fastshopping

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_shopping_list.*

class ShoppingListActivity : AppCompatActivity() {

    private var index : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        index = intent.getIntExtra(MainActivity.MESSAGE_LIST_ID, 0)
        listTitleText.text = MainActivity.LISTS[index].title

        val spinner = findViewById<Spinner>(R.id.shops_spinner)
        val shops = MainActivity.PRODUCT_DB.selectList ("Shops")
        val arraySpinner = shops.map { it.first }.toList()

        val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arraySpinner)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val adapterAuto= ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES)
        val textView = findViewById<AutoCompleteTextView>(R.id.productAutoComplete)
        textView.setAdapter(adapterAuto)

        productList.layoutManager = LinearLayoutManager(this)
        productList.adapter = ProductAdapter(MainActivity.LISTS[index].items)
    }

    fun addProduct(view: View) {

    }

    companion object {
        private val COUNTRIES = arrayOf("Belgium", "France", "Italy", "Germany", "Spain")
    }
}
