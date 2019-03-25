package com.c0ffee.fastshopping

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.widget.FrameLayout

class ProductView(ctx: Context): ConstraintLayout(ctx, null) {
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.product_view, this, true)
        layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
    }
}