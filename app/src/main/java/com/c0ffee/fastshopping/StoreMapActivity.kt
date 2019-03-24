package com.c0ffee.fastshopping

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_store_map.*

class StoreMapActivity : AppCompatActivity() {
    private lateinit var mapView: StoreMapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_map)

        mapView = StoreMapView(this)

        linear_layout.addView(mapView)
    }
}
