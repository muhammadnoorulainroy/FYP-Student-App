package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class ChildAdd1 : AppCompatActivity() {

    var fullName: TextInputLayout? = null
    var iAddress: TextInputLayout? = null
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    var datePicker: DatePicker? = null

    var Phone_No: String? = null
    var email: String? = null
    var hAddress: String? = null
    var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_add1)

        fullName = findViewById((R.id.cfull_Name))
        iAddress = findViewById((R.id.cIAddress))
        radioGroup = findViewById((R.id.cGender))
        datePicker = findViewById((R.id.cAge))

        val sessionManager =
            ParentSessionManager(this@ChildAdd1, ParentSessionManager.PSESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        Phone_No = userDetails[ParentSessionManager.PKEY_PHONE_NO]
        email = userDetails[ParentSessionManager.PKEY_EMAIL]
        hAddress = userDetails[ParentSessionManager.PKEY_HADDRESS]
        password = userDetails[ParentSessionManager.PKEY_PASSWORD]

    }

    fun createChildAccount(view: View) {
        if (!validateFullName() or !validateiAddressName() or !validateAge() or !validateGender()) {
            return
        }

        radioButton = findViewById(radioGroup!!.checkedRadioButtonId)
        val gender: String = " " + radioButton.text.toString();


        val day = datePicker!!.dayOfMonth
        val month = datePicker!!.month
        val year = datePicker!!.year
        val date = "$day/$month/$year"

        val getfName: String = fullName!!.editText!!.getText().toString().trim()
        val getIAddress: String = iAddress!!.editText!!.getText().toString().trim()

        try {
            val ref = FirebaseDatabase.getInstance().getReference("Children")
            val addNewUser = StudentDBHelperClass(
                getfName,
                email,
                hAddress,
                getIAddress,
                password,
                date,
                gender,
                Phone_No
            )
            ref.child(Phone_No!!).child(getfName).setValue(addNewUser)
        } catch (e: Exception) {
            println(e.message)
        }

        Toast.makeText(this, "Added Successfully!", Toast.LENGTH_SHORT).show()
        val intent = Intent(applicationContext, Parent_DashBoard::class.java)
        startActivity(intent)
        finish()


    }

    private fun validateiAddressName(): Boolean {
        val `val` = iAddress!!.editText!!.text.toString().trim { it <= ' ' }
        return if (`val`.isEmpty()) {
            iAddress!!.error = "Field can not be empty"
            false
        } else if (`val`.length < 15) {
            iAddress!!.setError("Address is too Short!");
            return false
        } else {
            iAddress!!.error = null
            iAddress!!.isErrorEnabled = false
            true
        }
    }

    private fun validateFullName(): Boolean {
        val `val` = fullName!!.editText!!.text.toString().trim { it <= ' ' }
        return if (`val`.isEmpty()) {
            fullName!!.error = "Field can not be empty"
            false
        } else {
            fullName!!.error = null
            fullName!!.isErrorEnabled = false
            true
        }
    }

    private fun validateGender(): Boolean {
        return if (radioGroup!!.checkedRadioButtonId === -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun validateAge(): Boolean {
        val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
        val userAge = datePicker!!.year
        val isAgeValid = currentYear - userAge
        return if (isAgeValid < 5 || isAgeValid > 20) {
            Toast.makeText(this, "You are not eligible to apply", Toast.LENGTH_SHORT).show()
            false
        } else true
    }

    fun callBack(view: View) {
        val intent = Intent(applicationContext, Parent_DashBoard::class.java)
        startActivity(intent)
        finish()
    }
}