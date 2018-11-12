package com.po1arbear.custom.celledittext

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cell_edit.setOnInputListener(object : CellEditText.OnInputListener {
            override fun onFinished(str: String) {
                Toast.makeText(this@MainActivity, "onFinish:$str", Toast.LENGTH_SHORT).show()
            }
        })

        keyboard.setOnItemClickListener(object : Keyboard.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    9 -> {//回退
                        cell_edit.delete()
                    }
                    11 -> {//完成
                        Toast.makeText(this@MainActivity, cell_edit.inputNumber, Toast.LENGTH_SHORT).show()
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