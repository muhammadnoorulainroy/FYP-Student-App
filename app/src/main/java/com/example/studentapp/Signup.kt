package com.example.studentapp

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import android.util.Pair as UtilPair

class Signup : AppCompatActivity() {
    var backbtn: ImageView? = null
    var nextBtn: Button? = null
    var Title: TextView? = null
    var progressBar: RelativeLayout? = null
    var scrollView: ScrollView? = null


    //For Validation Purpose
    var fullName: TextInputLayout? = null
    var email: TextInputLayout? = null
    var hAddress: TextInputLayout? = null
    var iAddress: TextInputLayout? = null
    var password: TextInputLayout? = null
    var _id: Int = 0
    var email_arrayList = ArrayList<String?>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Hooks
        backbtn = findViewById(R.id.signup_back_button)
        nextBtn = findViewById((R.id.next1_btn))
        progressBar = findViewById((R.id.signup_progress_bar))
        Title = findViewById((R.id.signup_title))
        fullName = findViewById((R.id.full_Name))
        email = findViewById((R.id.email_Name))
        hAddress = findViewById((R.id.home_address))
        iAddress = findViewById((R.id.insti_address))
        password = findViewById((R.id.password))
        scrollView = findViewById((R.id.sign_up_ScrollView))


        val checkUser: Query = FirebaseDatabase.getInstance().getReference("Students")
        checkUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (studentDS in snapshot.children) {

                    //progressBar!!.visibility = View.VISIBLE
                    var obj = studentDS.getValue(StudentDBHelperClass::class.java)
                    email_arrayList.add(obj!!.email)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Signup, error.message, Toast.LENGTH_SHORT).show();
            }
        })


    }

    fun callNextScreen(view: View) {
        if (!validateFullName() || !validateEmail() || !validatehAddressName() || !validateiAddressName() || !validatePassword()) {
            return
        } else {
            val getfName: String = fullName!!.editText!!.getText().toString().trim()
            val getEmail: String = email!!.editText!!.getText().toString().trim()
            val getHAddress: String = hAddress!!.editText!!.getText().toString().trim()
            val getIAddress: String = iAddress!!.editText!!.getText().toString().trim()
            val getPassword: String = password!!.editText!!.getText().toString().trim()


            val intent = Intent(applicationContext, SignUp2ndClass::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this, UtilPair.create(backbtn, "transition_back_button"),
                UtilPair.create(Title, "transition_crrate_button"),
                UtilPair.create(nextBtn, "transition_next_button")
            )
            intent.putExtra("fname", getfName)
            intent.putExtra("email", getEmail)
            intent.putExtra("hAddress", getHAddress)
            intent.putExtra("iAddress", getIAddress)
            intent.putExtra("password", getPassword)

            startActivity(intent, options.toBundle())

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

    private fun validateEmail(): Boolean {

        val `val` = email!!.getEditText()!!.getText().toString().trim()


        for (email in email_arrayList) {
            if (`val`.equals(email, ignoreCase = true)) {
                _id++
                break
            }
        }

        val checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+"
        if (`val`.isEmpty()) {
            email!!.setError("Field can not be empty")
            return false
        } else if (!`val`.matches((checkEmail).toRegex())) {
            email!!.setError("Invalid Email!")
            return false
        } else if (_id > 0) {
            email!!.error = "Email already exists!!!"
            println(_id)
            return false
        } else {
            email!!.setError(null)
            email!!.setErrorEnabled(false)
            return true
        }

    }

    private fun validatehAddressName(): Boolean {
        val `val` = hAddress!!.editText!!.text.toString().trim { it <= ' ' }
        return if (`val`.isEmpty()) {
            hAddress!!.error = "Field can not be empty"
            false
        } else if (`val`.length < 15) {
            hAddress!!.setError("Address is too Short!");
            return false
        } else {
            hAddress!!.error = null
            hAddress!!.isErrorEnabled = false
            true
        }
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

    private fun validatePassword(): Boolean {
        val `val` = password!!.getEditText()!!.getText().toString().trim()


        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(`val`)

        // return matcher.matches()
        if (`val`.isEmpty()) {
            password!!.setError("Field can not be empty")
            return false
        } else if (!matcher.matches()) {
            password!!.setError("Invalid Password Format!")
            return false
        } else if (`val`.length < 9) {
            password!!.setError("Password should contain atleast 8 characters!")
            return false
        } else {
            password!!.setError(null)
            password!!.setErrorEnabled(false)
            return true
        }
    }

    fun callBack(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)

        startActivity(intent)
    }

//    fun scrollDown(view: View) {
//        scrollView!!.fullScroll(ScrollView.FOCUS_DOWN)
//    }


}




