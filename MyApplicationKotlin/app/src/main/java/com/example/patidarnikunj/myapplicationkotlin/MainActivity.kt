package com.example.patidarnikunj.myapplicationkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lv = findViewById(R.id.listView) as ListView
        lv.adapter = ListExampleAdapter(this)

    }

    private class ListExampleAdapter(context: Context) : BaseAdapter() {
        internal var listOfArray = arrayOf("One", "Two", "Three", "Four", "Five", "Six", "Seven")
        private val mInflator: LayoutInflater

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        }

        override fun getItem(position: Int): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        }

        override fun getItemId(position: Int): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        }

        override fun getCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        }
    }

}
