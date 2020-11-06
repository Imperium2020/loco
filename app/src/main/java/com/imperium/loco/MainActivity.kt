package com.imperium.loco

import android.os.Build
import android.net.LinkAddress
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.databinding.DataBindingUtil
import com.imperium.loco.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateint var mainDatabind: ActivityMainBinding
    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        postTolist()

        mainDatabind.rv_recyclerView.layoutManager = LinearLayoutManager(this)
        mainDatabind.rv_recyclerView.adapter = RecyclerAdapter(titlesList,descList,imagesList)

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