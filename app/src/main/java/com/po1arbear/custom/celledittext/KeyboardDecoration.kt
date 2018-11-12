package com.po1arbear.custom.celledittext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View

class KeyboardDecoration(context: Context) : RecyclerView.ItemDecoration() {

    var mPaint: Paint = Paint()
    var dividerHeight: Int = 1

    init {
        mPaint.color = ContextCompat.getColor(context, android.R.color.holo_red_dark)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = dividerHeight
        outRect.bottom = dividerHeight
        outRect.left = dividerHeight
        outRect.right = dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount: Int = parent.childCount
        for (i in 0 until childCount) {
            val view: View = parent.getChildAt(i)
            val top = view.top + dividerHeight
            val bottom = view.bottom + dividerHeight
            val left = view.bottom + dividerHeight
            val right = view.bottom + dividerHeight
            c.drawRect(left.toFloat(), top.toFloat()
                    , right.toFloat(), bottom.toFloat(), mPaint)
        }

    }


}