package com.example.studentapp.ViewModel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.studentapp.Schedule
import com.example.studentapp.ViewDriver
import com.google.android.material.navigation.NavigationView

class DashBoard : AppCompatActivity() {


    var nametextView: TextView? = null
    var phonetextView: TextView? = null
    var mainNametextView: TextView? = null
    var drawerLayout: DrawerLayout? = null
    var navView: NavigationView? = null
    var headerView: View? = null
    var DriverSessionManager: String? = null
    var toolBar: androidx.appcompat.widget.Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        getWindow().setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

////        setContentView(R.layout.activity_dash_board)
////
////        //Hooks
////        drawerLayout = findViewById(R.id.drawer_layout)
////        navView = findViewById(R.id.nav_View)
////        toolBar = findViewById(R.id.toolbar)
////        mainNametextView = findViewById(R.id.textView)
//        headerView = navView!!.getHeaderView(0)
//        nametextView = headerView!!.findViewById(R.id.nametv)
//        phonetextView = headerView!!.findViewById(R.id.phonetv)
//
//        val sessionManager =
//            DriverSessionManager(this@DashBoard, DriverSessionManager.SESSION_USERSESSION)
//        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
//        val fullName = userDetails[DriverSessionManager.KEY_NAME]
//        val Phone_No = userDetails[DriverSessionManager.KEY_PHONE_NO]

        setSupportActionBar(toolBar)

        navView!!.bringToFront()
       // val toggle = ActionBarDrawerToggle(
//            this,
//            drawerLayout,
//            toolBar,
//            R.string.navogation_drawer_open,
//            R.string.navogation_drawer_closed
//        )
//        drawerLayout!!.addDrawerListener(toggle)
//        toggle.syncState()
//        navView!!.setCheckedItem(R.id.nav_home)
//        nametextView!!.setText(fullName)
//        phonetextView!!.setText("Driver")
//        mainNametextView!!.setText(fullName)
//        navView!!.setNavigationItemSelectedListener(this)

    }


    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            moveTaskToBack(true)
            // super.onBackPressed()
        }
    }

    fun callComplaint(view: View) {
        val intent = Intent(applicationContext, Complaints::class.java)
        startActivity(intent)
    }

    fun callAttendance(view: View) {
        val intent = Intent(applicationContext, Attendance::class.java)
        startActivity(intent)
    }

    fun callShifts(view: View) {
        val intent = Intent(applicationContext, Schedule::class.java)
        startActivity(intent)
    }

    fun callLogs(view: View) {
        val intent = Intent(applicationContext, Logs::class.java)
        startActivity(intent)
    }



    fun callViewStudents(view: View) {
        val intent = Intent(applicationContext, ViewDriver::class.java)
        startActivity(intent)
    }

}