package com.po1arbear.custom.celledittext

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.cell_edit.view.*

class CellEditText : LinearLayout {
    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private var inputNumber: StringBuilder = StringBuilder("")
    private var mOnInputListener: OnInputListener? = null

    init {
        init()
    }

    private fun init() {
        val view = View.inflate(context, R.layout.cell_edit, null)
        addView(view, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }


    fun setText(str: String) {
        Log.d("inputNumber", "str:" + str + "  length:" + inputNumber.length)
        when (inputNumber.length) {
            0 -> {
                inputNumber.append(str)
                tv_pay1.text = str
            }
            1 -> {
                inputNumber.append(str)
                tv_pay2.text = str

            }
            2 -> {
                inputNumber.append(str)
                tv_pay3.text = str


            }
            3 -> {
                inputNumber.append(str)
                tv_pay4.text = str
            }
            4 -> {
                inputNumber.append(str)
                tv_pay5.text = str
            }
            5 -> {
                inputNumber.append(str)
                tv_pay6.text = str

                if (mOnInputListener != null) {
                    mOnInputListener!!.onFinished(inputNumber.toString())
                }
            }

        }

    }

    fun delete() {
        when (inputNumber.length) {
            0 -> {
            }
            1 -> {
                inputNumber.replace(0, 1, "")
                tv_pay1.text = ""
            }
            2 -> {
                inputNumber.replace(1, 2, "")
                tv_pay2.text = ""
            }
            3 -> {
                inputNumber.replace(2, 3, "")
                tv_pay3.text = ""
            }
            4 -> {
                inputNumber.replace(3, 4, "")
                tv_pay4.text = ""
            }
            5 -> {
                inputNumber.replace(4, 5, "")
                tv_pay5.text = ""
            }
            6 -> {
                inputNumber.replace(5, 6, "")
                tv_pay6.text = ""
            }

        }
        Log.d("inputNumber", "length:" + inputNumber.length)
        Toast.makeText(context, inputNumber, Toast.LENGTH_SHORT).show()

    }


    fun setOnInputListener(listener: OnInputListener) {
        mOnInputListener = listener
    }

    interface OnInputListener {
        private fun onInput() {

        }

        fun onFinished(str: String)
    }
}