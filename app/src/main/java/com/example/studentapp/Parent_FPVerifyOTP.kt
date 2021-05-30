package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.chaos.view.PinView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class Parent_FPVerifyOTP : AppCompatActivity() {
    var Title: TextView? = null
    var codeBySystem: String? = null
    var Phone_No: String? = ""
    var pinFromUser: PinView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_fpverify_otp)

        //Hooks
        Title = findViewById((R.id.pfpvverify_desc))
        pinFromUser = findViewById(R.id.pfpvpin_view)
        Phone_No = intent.getStringExtra("phone") ?: ""
        Title!!.text = "Enter one time code sent on your phone No: " + Phone_No

        sendVerificationCodeToUser(Phone_No!!)
    }

    fun setNewPasswordBtn(view: View) {
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

    private val mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(
                s: String,
                forceResendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
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
                Toast.makeText(this@Parent_FPVerifyOTP, e.message, Toast.LENGTH_SHORT).show()
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
                        this@Parent_FPVerifyOTP,
                        "Verification Completed!",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(applicationContext, Parent_SetNewPassword::class.java)
                    intent.putExtra("phone", Phone_No)
                    startActivity(intent)
                    finish()

                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(
                            this@Parent_FPVerifyOTP,
                            "Verification not Completed! Try Again.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }

    fun callBack(view: View) {
        val intent = Intent(applicationContext, Parent_login::class.java)
        startActivity(intent)
        finish()
    }
    fun callResendPIN(view: View) {
        sendVerificationCodeToUser(Phone_No!!)
    }
}