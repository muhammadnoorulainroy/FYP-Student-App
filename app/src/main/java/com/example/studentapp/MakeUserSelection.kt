package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager

class MakeUserSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_make_user_selection)
    }

    fun callStudentApp(view: View)
    {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    fun callParentApp(view: View) {
        val intent = Intent(applicationContext, Parent_MainActivity::class.java)
        startActivity(intent)
    }
}