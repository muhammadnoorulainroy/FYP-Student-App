package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class P_Complaints : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pcomplaints)
    }

    fun viewExistingComplaints(view: View) {}
    fun addNewComplaint(view: View) {
        val intent = Intent(applicationContext, P_Add_Complaint::class.java)
        startActivity(intent)
    }
    fun callBack(view: View) {}
}