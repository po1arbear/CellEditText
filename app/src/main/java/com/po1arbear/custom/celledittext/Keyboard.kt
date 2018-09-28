package com.po1arbear.custom.celledittext

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_keyboard.view.*

class Keyboard : RelativeLayout {
    private var mAdapter: MyAdapter? = null
    val keys = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "0", "完成")

    companion object {
        const val TYPE_COMMON = 996
        const val TYPE_DELET = 965
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, style: Int) : super(context, attributeSet, style) {
        init()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        val mWidth = 400
        val mHeight = 400

        // 当布局参数设置为wrap_content时，设置默认值
        if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT && layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight)
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize)
        } else if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight)
        }

    }

    private fun init() {
        val view = View.inflate(context, R.layout.layout_keyboard, null)
        addView(view, LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        mAdapter = MyAdapter()
        recycler_view.layoutManager = GridLayoutManager(context, 3)
        recycler_view.addItemDecoration(KeyboardDecoration(context))
        recycler_view.adapter = mAdapter
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MyViewHolder {
            when (viewType) {
                TYPE_COMMON -> {
                    val itemView = View.inflate(context, R.layout.common_key, null)
                    return MyViewHolder(itemView)
                }
                TYPE_DELET -> {
                    val itemView = View.inflate(context, R.layout.delete_key, null)
                    return MyViewHolder(itemView)
                }

            }
            return MyViewHolder(View(context))

        }

        override fun getItemCount(): Int {
            return keys.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            when (holder.itemViewType) {
                TYPE_COMMON -> {
                    holder.itemView.findViewById<TextView>(R.id.tv_key).text = keys[position]
                    holder.itemView.findViewById<View>(R.id.tv_key).setOnClickListener {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener!!.onItemClick(position)
                        }
                    }
                }

                TYPE_DELET -> {
                    holder.itemView.findViewById<View>(R.id.ll_delete).setOnClickListener {
                        mOnItemClickListener!!.onItemClick(position)

                    }
                }
            }

        }

        override fun getItemViewType(position: Int): Int {
            return if (position == 9) {
                TYPE_DELET
            } else {
                TYPE_COMMON
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


    private var mOnItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mOnItemClickListener = listener
    }

}