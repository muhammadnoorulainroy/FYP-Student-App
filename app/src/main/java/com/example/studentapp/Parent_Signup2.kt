package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class Parent_Signup2 : AppCompatActivity() {
    var backbtn: ImageView? = null
    var nextBtn: Button? = null
    var Title: TextView? = null
    var ph_number: TextInputLayout? = null

    var getfName: String? = ""
    var getEmail: String? = ""
    var getHAddress: String? = ""
    var getPassword: String? = ""
    var _id: Int = 0
    var phone_arrayList = ArrayList<String?>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_signup2)

        backbtn = findViewById(R.id.psignup2_back_button)
        nextBtn = findViewById((R.id.ps2next1_btn))
        Title = findViewById((R.id.psignup2_title))
        ph_number = findViewById((R.id.pph_number))

        getfName = intent.getStringExtra("fname")
        getEmail = intent.getStringExtra("email")
        getHAddress = intent.getStringExtra("hAddress")
        getPassword = intent.getStringExtra("password")

        val checkUser: Query = FirebaseDatabase.getInstance().getReference("Parents")
        checkUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (studentDS in snapshot.children) {

                    //progressBar!!.visibility = View.VISIBLE
                    var obj = studentDS.getValue(ParentDBHelperClass::class.java)
                    phone_arrayList.add(obj!!.phone_No)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Parent_Signup2, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
    }

    fun callNextScreen(view: View) {

        if (!validatePhoneNumber()) {
            return
        }
        var phone: String = ph_number!!.editText!!.getText().toString().trim()
        if (phone.get(0) == '0') {
            phone = phone.substring(1)
        }
        val getPhone: String = "+92" + phone

        val intent = Intent(this@Parent_Signup2, Parent_Verify_OTP::class.java)

//        val getfName = intent.getStringExtra("fname")
//        val getEmail = intent.getStringExtra("email")
//        val getHAddress = intent.getStringExtra("hAddress")
//        val getIAddress = intent.getStringExtra("iAddress")
//        val getPassword = intent.getStringExtra("password")
//        val getAge = intent.getStringExtra("age")
//        val getGender = intent.getStringExtra("gender")


        intent.putExtra("fname", getfName)
        intent.putExtra("email", getEmail)
        intent.putExtra("hAddress", getHAddress)
        intent.putExtra("password", getPassword)
        intent.putExtra("phone", getPhone)

//        print(getGender+" "+getfName)

        startActivity(intent)

    }

    private fun validatePhoneNumber(): Boolean {
        var `val`: String = "+92" + ph_number!!.getEditText()!!.getText().toString().trim()


        for (phone in phone_arrayList) {
            if(`val`.equals(phone))
            {
                _id++
                break
            }
        }

        val pattern: Pattern
        val matcher: Matcher
        val Phone_PATTERN = "^[+]?[0-9]{10,13}\$"
        pattern = Pattern.compile(Phone_PATTERN)
        matcher = pattern.matcher(`val`)

        return if (`val`.isEmpty()) {
            ph_number!!.setError("Enter valid phone number!")
            false
        } else if (!matcher.matches()) {
            ph_number!!.setError("Invalid Phone Number!")
            return false
        } else if (_id > 0) {
            ph_number!!.setError("Phone Number already Exist!")
            return false
        } else if (`val`.length > 14) {
            ph_number!!.setError("Invalid Phone No!")
            false
        } else {
            ph_number!!.setError(null)
            ph_number!!.setErrorEnabled(false)
            true
        }
    }

    fun callBack(view: View) {
        val intent = Intent(applicationContext, Parent_Signup::class.java)

        startActivity(intent)
    }

}
