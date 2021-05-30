package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MakeSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_selection)
    }

    fun callOTPScreen(view: View) {
        val intent = Intent(applicationContext, FPVerifyOTP::class.java)

        startActivity(intent)
    }
    fun callBack(view: View) {
        val intent = Intent(applicationContext, Login::class.java)

        startActivity(intent)
    }
}