package com.po1arbear.custom.celledittext

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cell_edit.setOnInputListener(object : CellEditText.OnInputListener{
            override fun onFinished(str: String) {
                Toast.makeText(this@MainActivity, "onFinish:$str", Toast.LENGTH_SHORT).show()
            }
        })

        input.setOnClickListener{
            cell_edit.setText("1")
        }

        delete.setOnClickListener {
            cell_edit.delete()
        }

    }
}