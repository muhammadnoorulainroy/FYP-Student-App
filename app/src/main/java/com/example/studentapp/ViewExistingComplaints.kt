package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.bold
import androidx.core.text.italic
import com.google.firebase.database.*
import java.util.HashMap

class ViewExistingComplaints : AppCompatActivity() {

    var complaints: TextView? = null
    var id: Int = 0
    var phone_No: String? = null
    var view: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_existing_complaints)

        //Hooks
        complaints = findViewById(R.id.existing_Complaints)
        view = findViewById(R.id.view)
        val sessionManager =
            SessionManager(this@ViewExistingComplaints, SessionManager.SESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        phone_No = userDetails[SessionManager.KEY_PHONE_NO]
        complaints!!.movementMethod = ScrollingMovementMethod()
        var key: String = "Student " + phone_No

        //Database Query
        val checkUser: Query =
            FirebaseDatabase.getInstance().getReference("Complaints").child(key)
        checkUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (studentDS in snapshot.children) {

                    //progressBar!!.visibility = View.VISIBLE
                    var obj = studentDS.getValue(ComplaintHelperClass::class.java)
                    if (obj!!.userPhone_No.equals(phone_No) && obj!!.userClass.equals("Student")) {
                        id++

                        val s = SpannableStringBuilder()
                            .bold { append("Complaint No: " + id) }
                            .bold { append("\n\nTitle: ") }
                            .append(obj!!.title)
                            .bold { append("\n" + "Description: ") }
                            .append(obj!!.description)
                            .bold { append("\n" + "Date: ") }
                            .append(obj!!.date)
                            .bold { append("\n" + "Status: ") }
                            .bold { italic { append(obj!!.status) } }
                            .append("\n\n_____________________________________" + "\n\n")
                        complaints!!.append(s)

//                        complaints!!.append(
//                            "Complaint No: " + id +
//                                    "\n\nTitle: " + obj!!.title
//                                    + "\nDescription: " + obj!!.description
//                                    + "\nDate: " + obj!!.date
//                                    + "\nStatus: " + obj!!.status
//                                    + "\n\n_____________________________________" + "\n\n"
//                        )
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewExistingComplaints, error.message, Toast.LENGTH_SHORT)
                    .show();
            }
        })
    }

    fun callBack(view: View) {
        val intent = Intent(applicationContext, Complaints::class.java)
        startActivity(intent)
        finish()
    }
}