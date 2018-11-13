package com.po1arbear.custom.celledittext

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_key.setOnClickListener {
            startActivity(Intent(this, KeyboardActivity::class.java))
        }

        btn_cell.setOnClickListener {
            startActivity(Intent(this, CellInputActivity::class.java))
        }

    }
}