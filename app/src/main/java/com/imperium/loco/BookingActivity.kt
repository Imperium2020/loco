package com.imperium.loco

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imperium.loco.database.AppDatabase
import com.imperium.loco.databinding.ActivityBookingBinding

class BookingActivity : AppCompatActivity() {
    private lateinit var layoutBinding: ActivityBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking)

        val trainDao = AppDatabase.getInstance(this).trainDao
        val fromArrayList: List<String> = trainDao.getAllFromStations().toList()
        val toArrayList: List<String> = trainDao.getAllToStations().toList()

        val fromStationSpinner: Spinner = layoutBinding.fromStation
        fromStationSpinner.adapter = ArrayAdapter(
            this@BookingActivity,
            android.R.layout.simple_spinner_item,
            fromArrayList
        ).apply {
            setDropDownViewResource(R.layout.item_spinner_dropdown)
        }

        val toStationSpinner: Spinner = layoutBinding.toStation
        toStationSpinner.adapter = ArrayAdapter(
            this@BookingActivity,
            android.R.layout.simple_spinner_item,
            toArrayList
        ).apply {
            setDropDownViewResource(R.layout.item_spinner_dropdown)
        }


    }
}