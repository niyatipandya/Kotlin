package com.example.patidarnikunj.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lv = findViewById(R.id.listView) as ListView
        lv.adapter = ListExampleAdapter(this)


    }
}
