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
import com.imperium.loco.databinding.ActivityLoginBinding
import java.util.regex.Pattern
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity() {
    private lateinit var layoutBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        layoutBinding.btnLogin.setOnClickListener {
            val userName = layoutBinding.username.text.toString()
            val password = layoutBinding.password.text.toString()

            if (validateInput(userName, password)) {
                val appDb: AppDatabase = AppDatabase.getInstance(applicationContext)
                val dao: AppDatabaseDao = appDb.appDatabaseDao
                thread {
                    val user = dao.login(userName)
                    when {
                        user == null -> putToast("User not Found! Please Register.")
                        user.password != password -> putToast("Invalid credentials")
                        else -> {
                            putToast("Welcome to ${getString(R.string.app_name)}, $userName")
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java
                                ).putExtra("userId", user.userId)
                            )
                            finish()
                        }
                    }
                }
            }
        }
        layoutBinding.btnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun putToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_LONG).show()
        }
    }

    private fun validateInput(userName: String, password: String): Boolean {
        val passChecker = listOf(
            "^(.{0,7}|.{21,})$",
            "^([^A-Za-z]*)$",
            "^([^0-9]*)$",
            "^([a-zA-Z0-9]*)$",
            "[^\\s]*\\s.*"
        ).map { Pattern.compile(it).matcher(password).matches() }

        val msg = when {
            userName.isEmpty() -> "Username not entered."
            passChecker[0] -> "Length of Password should be in range (8, 20)"
            passChecker[1] -> "Password should contain at least one alphabet"
            passChecker[2] -> "Password should contain at least one digit"
            passChecker[3] -> "Password should contain at least one special character."
            passChecker[4] -> "Password should not contain spaces."
            else -> null
        }
        return if (msg == null) true
        else {
            putToast(msg)
            false
        }
    }
}