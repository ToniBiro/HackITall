package com.c0ffee.fastshopping

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)


        var s = "Products: \n"
        val products = db.selectList ("Shops")
        for (i in products) {
            s += i.first + " " + i.second.toString() + "\n"
        }
        s += "\nShops\n"
        val shops = db.selectList ("Products")
        for (i in shops) {
            s += i.first + " " + i.second.toString() + "\n"
        }
        sample_text.text = s
    }
}
