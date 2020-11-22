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
import com.imperium.loco.database.User
import com.imperium.loco.database.UserDao
import com.imperium.loco.databinding.ActivityRegisterBinding
import java.util.regex.Pattern
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
                val dao: UserDao = appDb.userDao
                thread {
                    when (dao.login(regUser.userName)) {
                        null -> {
                            dao.createUser(regUser)
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                            finish()
                        }
                        regUser -> putToast("User already Exist!")
                        else -> putToast("Username unavailable.")
                    }
                }

            }
        }
        layoutBinding.loginButton.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun putToast(msg: String?): Boolean {
        if (msg.isNullOrEmpty()) return true
        runOnUiThread {
            Toast.makeText(this@RegisterActivity, msg, Toast.LENGTH_LONG).show()
        }
        return false
    }

    private fun validateInput(user: User): Boolean {

        val emailParse = Pattern.compile("^([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_.-]+).([a-zA-Z]{2,5})$")
            .matcher(user.email)
        val test1 = putToast(
            when {
                user.fullName.isEmpty() -> "Full Name not entered."
                user.userName.isEmpty() -> "Username not entered."
                user.email.isEmpty() or !emailParse.matches() -> "Email is not valid."
                user.phone.length != 10 -> "Phone Number is not valid."
                else -> null
            }
        )
        if (!test1) return false

        val passChecker = listOf(
            "^(.{0,7}|.{21,})$",
            "^([^A-Za-z]*)$",
            "^([^0-9]*)$",
            "^([a-zA-Z0-9]*)$",
            "[^\\s]*\\s.*"
        ).map { Pattern.compile(it).matcher(user.password).matches() }

        return putToast(
            when {
                passChecker[0] -> "Length of Password should be in range (8, 20)"
                passChecker[1] -> "Password should contain at least one alphabet"
                passChecker[2] -> "Password should contain at least one digit"
                passChecker[3] -> "Password should contain at least one special character."
                passChecker[4] -> "Password should not contain spaces."
                else -> null
            }
        )
    }
}
