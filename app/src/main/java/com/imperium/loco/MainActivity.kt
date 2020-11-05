package com.imperium.loco

import android.net.LinkAddress
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postTolist()

        rv_recyclerView.layoutManager = LinearLayoutManager(this)
        rv_recyclerView.adapter = RecyclerAdapter(titlesList,descList,imagesList)

    }
    private fun addToList(title: String,description: String,image: Int){
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
    }

    private fun postTolist() {
            addToList("BOOKING","Ticket booking", R.mipmap.ic_launcher_round)
            addToList("CANCELLATION","Cancel ticket", R.mipmap.ic_launcher_round)
            addToList("SCHEDULE","Train timings", R.mipmap.ic_launcher_round)
            addToList("VIEW TICKET","check ticket status", R.mipmap.ic_launcher_round)

    }
}