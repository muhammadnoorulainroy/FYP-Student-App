package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Parent_FP_SuccessMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_fp_success_message)
    }

    fun backToLogin(view: View) {
        val intent = Intent(applicationContext, Parent_login::class.java)
        startActivity(intent)
        finish()
    }
    fun callBack(view: View) {
        val intent = Intent(applicationContext, Parent_login::class.java)
        startActivity(intent)
        finish()
    }

}