package com.c0ffee.fastshopping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_shopping_list.*
import kotlinx.android.synthetic.main.product_view.*
import kotlinx.android.synthetic.main.product_view.view.*

class ShoppingListActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

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

        val products = MainActivity.PRODUCT_DB.selectList("Products")
                .map { it.first }.distinct()

        val adapterAuto= ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, products)
        val textView = findViewById<AutoCompleteTextView>(R.id.productAutoComplete)
        textView.setAdapter(adapterAuto)

        productList.layoutManager = LinearLayoutManager(this)
        productList.adapter = ProductAdapter(this, MainActivity.LISTS[index].items)

        spinner.onItemSelectedListener = this
    }

    val currentShop: String
        get() = shops_spinner.selectedItem.toString()

    override fun onNothingSelected(parent: AdapterView<*>?) {
        productList.adapter!!.notifyDataSetChanged()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        productList.adapter!!.notifyDataSetChanged()
    }

    fun addProduct(view: View) {
        val product = productAutoComplete.text.toString()
        if (MainActivity.PRODUCT_DB.testProduct(product) && MainActivity.LISTS[index].items.indexOf(product) == -1) {
            MainActivity.LISTS[index].items.add(product)
            MainActivity.LIST_DB.onUpdate(MainActivity.LISTS)
            productList.adapter!!.notifyDataSetChanged()
        }
    }
    fun openMap(view: View) {
        val intent = Intent(this, StoreMapActivity::class.java)
        intent.putExtra(MainActivity.MESSAGE_LIST_ID, index)
        startActivity(intent)
    }

    fun deleteProduct(view: View) {
        val i = productList.findContainingViewHolder(view)!!.adapterPosition
        MainActivity.LISTS[index].items.removeAt(i)
        MainActivity.LIST_DB.onUpdate(MainActivity.LISTS)
        productList.adapter!!.notifyDataSetChanged()
    }
}

