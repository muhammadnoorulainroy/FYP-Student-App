package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaos.view.PinView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit

class Verify_OTP : AppCompatActivity() {
    var Title: TextView? = null
    var pinFromUser: PinView? = null
    var codeBySystem: String? = null
    var FullName: String? = ""
    var Email: String? = ""
    var H_Address: String? = ""
    var I_Address: String? = ""
    var Password: String? = ""
    var Dob: String? = ""
    var Gender: String? = ""
    var Phone_No: String? = ""
//    private lateinit var reference: DatabaseReference
//    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)

        pinFromUser = findViewById(R.id.pin_view)
        Title = findViewById((R.id.verify_otp_title))
        FullName = intent.getStringExtra("fname") ?: ""
        Email = intent.getStringExtra("email") ?: ""
        H_Address = intent.getStringExtra("hAddress") ?: ""
        I_Address = intent.getStringExtra("iAddress") ?: ""
        Password = intent.getStringExtra("password") ?: ""
        Dob = intent.getStringExtra("age") ?: ""
        Gender = intent.getStringExtra("gender") ?: ""
        Phone_No = intent.getStringExtra("phone") ?: ""

        Title!!.text = "Enter one time code sent on your phone No: " + Phone_No





        sendVerificationCodeToUser(Phone_No!!)
    }

    fun callNextScreen(view: View) {
        var code: String = pinFromUser!!.text.toString();
        if (!code.isEmpty()) {
            verfyCode(code);
        }

    }

    private fun sendVerificationCodeToUser(phone_no: String) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone_no)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val mCallbacks: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                codeBySystem = s
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                if (code != null) {
                    pinFromUser!!.setText(code)
                    verfyCode(code)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@Verify_OTP, e.message, Toast.LENGTH_SHORT).show()
            }
        }


    private fun verfyCode(code: String) {
        val credentials = PhoneAuthProvider.getCredential(codeBySystem!!, code)
        signInUsingCredentials(credentials)
    }

    private fun signInUsingCredentials(credential: PhoneAuthCredential) {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@Verify_OTP,
                        "Verification Completed!",
                        Toast.LENGTH_SHORT
                    ).show()
                    storeNewUserData()
                    //create a Session
                    val sessionManager =
                        SessionManager(this@Verify_OTP, SessionManager.SESSION_USERSESSION)
                    sessionManager.createLoginSession(
                        FullName,
                        Email,
                        H_Address,
                        I_Address,
                        Dob,
                        Gender,
                        Phone_No,
                        Password
                    )

                    val sessionManager2 =
                        SessionManager(this@Verify_OTP, SessionManager.SESSION_REMEMBERME)
                    sessionManager2.createRememberMeSession(
                        Phone_No,
                        Password
                    )
                    Toast.makeText(this@Verify_OTP, "Welocome $FullName", Toast.LENGTH_SHORT)
                        .show();
                    val intent = Intent(applicationContext, DashBoard::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(
                            this@Verify_OTP,
                            "Verification not Completed! Try Again.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }


    private fun storeNewUserData() {
        try {
            val ref = FirebaseDatabase.getInstance().getReference("Students")
            val addNewUser = StudentDBHelperClass(
                FullName,
                Email,
                H_Address,
                I_Address,
                Password,
                Dob,
                Gender,
                Phone_No
            )
            //val id = ref.push().key
            ref.child(Phone_No!!).setValue(addNewUser)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun callBack(view: View) {
        val intent = Intent(applicationContext, Login::class.java)

        startActivity(intent)
        finish()
    }

    fun callResendPIN(view: View) {
        sendVerificationCodeToUser(Phone_No!!)
    }

}