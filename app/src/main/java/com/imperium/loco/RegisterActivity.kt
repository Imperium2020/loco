package com.imperium.loco

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imperium.loco.database.AppDatabase
import com.imperium.loco.database.AppDatabaseDao
import com.imperium.loco.database.User
import com.imperium.loco.databinding.ActivityRegisterBinding
import kotlin.concurrent.thread

class RegisterActivity : AppCompatActivity() {
    private lateinit var layoutBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        layoutBinding.registerButton.setOnClickListener {
            val regUser = User(
                fullName = layoutBinding.name.text.toString(),
                email = layoutBinding.email.text.toString(),
                password = layoutBinding.password.text.toString(),
                phone = layoutBinding.phoneNo.text.toString(),
                userName = layoutBinding.username.text.toString()
            )
            if (validateInput(regUser)) {
                val appDb: AppDatabase = AppDatabase.getInstance(applicationContext)
                val dao: AppDatabaseDao = appDb.appDatabaseDao
                thread {
                    dao.createUser(regUser)
                    runOnUiThread {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Welcome to Loco, ${regUser.userName}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateInput(user: User): Boolean {
        val empty = when {
            user.fullName.isEmpty() -> "fullName"
            user.email.isEmpty() -> "email"
            user.password.isEmpty() -> "password"
            user.phone.isEmpty() -> "phone"
            user.userName.isEmpty() -> "userName"
            else -> "ok"
        }
        return if (empty == "ok") true
        else {
            Toast.makeText(this@RegisterActivity, "$empty is not entered!!", Toast.LENGTH_LONG)
                .show()
            false
        }
    }
}