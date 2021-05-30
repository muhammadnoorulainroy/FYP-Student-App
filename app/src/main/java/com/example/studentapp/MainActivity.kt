package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
    }


    fun callLoginScreen(View: View?) {
        //        val pairs: Array<Pair<View, String>?> = arrayOfNulls(1)
//        pairs[0] =
//            android.util.Pair<View, String>(findViewById(R.id.login_btn), "transition_Login")

        // var options = ActivityOptions.makeSceneTransitionAnimation(Login, *pairs)
        val intent = Intent(applicationContext, Login::class.java)
        startActivity(intent)
    }

    fun callSignUpScreen(view: View) {
        val intent = Intent(applicationContext, Signup::class.java)
        startActivity(intent)
    }


}

