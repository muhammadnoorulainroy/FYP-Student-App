package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate

class Parent_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_parent_main)
    }


    fun callLoginScreen(View: View?) {
        val intent = Intent(applicationContext, Parent_login::class.java)
        startActivity(intent)
    }

    fun callSignUpScreen(view: View) {
        val intent = Intent(applicationContext, Parent_Signup::class.java)
        startActivity(intent)
    }
}