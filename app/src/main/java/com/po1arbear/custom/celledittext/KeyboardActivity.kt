package com.po1arbear.custom.celledittext

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_keyboard.*

class KeyboardActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyboard)

        cell_edit.setOnInputListener(object : CellEditText.OnInputListener {
            override fun onFinished(str: String) {
                Toast.makeText(this@KeyboardActivity, "onFinish:$str", Toast.LENGTH_SHORT).show()
            }
        })

        keyboard.setOnItemClickListener(object : Keyboard.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    9 -> {//回退
                        cell_edit.delete()
                    }
                    11 -> {//完成
                        Toast.makeText(this@KeyboardActivity, cell_edit.inputNumber, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        val value = keyboard.keys[position]
                        cell_edit.setText(value)
                    }
                }
            }
        })

    }

}