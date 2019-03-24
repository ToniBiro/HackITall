package com.c0ffee.fastshopping

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class StoreMapView(ctx: Context): View(ctx) {
    private val paint = Paint()
    private val aisles = listOf(
            Rect(100, 160, 200, 190),
            Rect(240, 170, 310, 200),
            Rect(250, 140, 300, 160),
            Rect(350, 140, 400, 170),
            Rect(300, 50, 350, 130),
            Rect(140, 60, 200, 120),
            Rect(200, 0, 250, 30)
            )

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        paint.strokeWidth = 3.0f

        paint.color = Color.BLACK

        aisles.forEachIndexed { idx, it ->
            var left = it.top.toFloat()
            var top = it.left.toFloat()
            var right = it.bottom.toFloat()
            var bottom = it.right.toFloat()

            left *= 4.0f
            right *= 4.0f
            top *= 4.0f
            bottom *= 4.0f

            left += 50.0f
            right += 50.0f

            paint.color = Color.CYAN
            canvas.drawRect(left, top, right, bottom, paint)

            paint.color = Color.BLACK
            paint.textSize = 36.0f
            canvas.drawText(idx.toString(), (left + right) / 2.0f, (top + bottom) / 2.0f, paint);
        }
    }
}