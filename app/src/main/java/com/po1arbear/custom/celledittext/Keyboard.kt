package com.po1arbear.custom.celledittext

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_keyboard.view.*

class Keyboard : RelativeLayout {
    var mAdapter: MyAdapter? = null
    val keys = arrayOf("1","2","3","4","5","6","7","8","9","10","0","完成")

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

    private fun init() {
        val view = View.inflate(context, R.layout.layout_keyboard, null)
        addView(view, LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
        mAdapter = MyAdapter()
        recycler_view.layoutManager = GridLayoutManager(context,3)
        recycler_view.addItemDecoration(DefaultItemDecoration(context))
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
            when(holder.itemViewType){
                TYPE_COMMON->{
                    holder.itemView.findViewById<TextView>(R.id.tv_key).text = keys[position]
                }
                TYPE_DELET->{

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


}