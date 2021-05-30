package com.example.studentapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.util.*

class P_Add_Complaint : AppCompatActivity() {
    var c_title: TextInputLayout? = null
    var c_desc: TextInputLayout? = null
    var phone_No: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_padd_complaint)

        //Hooks
        c_title = findViewById((R.id.p_text_complaint_title))
        c_desc = findViewById((R.id.p_complaint_description))


        val sessionManager =
            ParentSessionManager(this@P_Add_Complaint, ParentSessionManager.PSESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        phone_No = userDetails[ParentSessionManager.PKEY_PHONE_NO]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun submitComplaints(view: View) {
        if (!validateTitle() || !validateDescrition()) {
            return
        }
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)


        var date: String = LocalDate.of(year!!, month!!, day!!).toString()
        val getTitle: String = c_title!!.editText!!.getText().toString().trim()
        val getDesc: String = c_desc!!.editText!!.getText().toString().trim()
        val status: String = "Pending"
        val userClass: String = "Parent "
        val key: String = userClass + phone_No

        try {
            val ref = FirebaseDatabase.getInstance().getReference("Complaints")
            val addNewUser = ComplaintHelperClass(
                getTitle,
                getDesc,
                date,
                status,
                phone_No,
                userClass
            )

            ref.child(key!!).setValue(addNewUser)

        } catch (e: Exception) {
            println(e.message)
        }
        Toast.makeText(
            this@P_Add_Complaint,
            "Complaint submitted successfully!",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(applicationContext, P_Complaints::class.java)
        startActivity(intent)
        finish()


    }


    fun callBack(view: View) {
        val intent = Intent(applicationContext, P_Complaints::class.java)
        startActivity(intent)
        finish()
    }

    //Validate Functions
    private fun validateDescrition(): Boolean {
        val `val` = c_desc!!.editText!!.text.toString().trim { it <= ' ' }
        return if (`val`.isEmpty()) {
            c_desc!!.error = "Field can not be empty!"
            false
        } else if (`val`.length < 15) {
            c_desc!!.setError("Description is too Short!");
            return false
        } else {
            c_desc!!.error = null
            c_desc!!.isErrorEnabled = false
            true
        }
    }

    private fun validateTitle(): Boolean {
        val `val` = c_title!!.editText!!.text.toString().trim { it <= ' ' }
        return if (`val`.isEmpty()) {
            c_title!!.error = "Field can not be empty!"
            false
        } else if (`val`.length < 3) {
            c_title!!.setError("Title is too Short!");
            return false
        } else {
            c_title!!.error = null
            c_title!!.isErrorEnabled = false
            true
        }
    }
}