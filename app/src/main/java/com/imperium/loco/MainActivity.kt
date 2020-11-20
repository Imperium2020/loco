package com.imperium.loco

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.imperium.loco.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainDataBind: ActivityMainBinding
    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainDataBind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        postToList()

        mainDataBind.rvRecyclerView.layoutManager = LinearLayoutManager(this)
        mainDataBind.rvRecyclerView.adapter = RecyclerAdapter(titlesList, descList, imagesList)

    }

    private fun addToList(title: String, description: String) {
        titlesList.add(title)
        descList.add(description)
        imagesList.add(R.mipmap.ic_launcher_round)
    }

    private fun postToList() {
        addToList("BOOKING", "Ticket booking")
        addToList("CANCELLATION", "Cancel ticket")
        addToList("SCHEDULE", "Train timings")
        addToList("VIEW TICKET", "check ticket status")

    }
}