package com.c0ffee.fastshopping

import android.content.Context
import android.support.v7.widget.CardView
import android.view.LayoutInflater

class ListView(ctx: Context) : CardView(ctx, null) {
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.list_view, this, true)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }
}