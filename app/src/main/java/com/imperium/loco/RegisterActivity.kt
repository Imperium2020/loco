package com.imperium.loco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

        fun LoginID(view: View) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
    }
}