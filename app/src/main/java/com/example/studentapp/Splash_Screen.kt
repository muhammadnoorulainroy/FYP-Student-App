package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Splash_Screen : AppCompatActivity() {
    var topAnim: Animation? = null
    var bottomAnim: Animation? = null
    var image: ImageView? = null
    var logo: TextView? = null
    var slogan: TextView? = null
    private val SPASH_SCREEN: Long = 2700

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash_screen)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)


        image = findViewById(R.id.imageView)
        logo = findViewById(R.id.textView1)
        slogan = findViewById(R.id.textView2)

        image!!.setAnimation(topAnim)
        logo!!.setAnimation(bottomAnim)
        slogan!!.setAnimation(bottomAnim)


        Handler().postDelayed(
            {
//                val sessionManager =
//                    SessionManager(this@Splash_Screen, SessionManager.SESSION_REMEMBERME)
//                if (sessionManager.checkRememberMe()) {
//                    val intent = Intent(this@Splash_Screen, Long::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
                    val intent = Intent(this@Splash_Screen, MakeUserSelection::class.java)
                    startActivity(intent)
                    finish()
//                }
            },
            SPASH_SCREEN
        )
    }
}