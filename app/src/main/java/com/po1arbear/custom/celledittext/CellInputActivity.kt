package com.po1arbear.custom.celledittext

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cell_input.*

class CellInputActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cell_input)
    }


    override fun onResume() {
        super.onResume()
        cell_input_view.showKeyboard()
    }
}