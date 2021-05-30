package com.example.studentapp


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*


class Parent_DashBoard : AppCompatActivity() {
    var fullName: String? = null
    var Phone_No: String? = null
    var email: String? = null
    var hAddress: String? = null
    var recyclerView: RecyclerView? = null
    var adapter: ChildAdapterClass? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_dash_board)

        setTitle("Childrens")

        recyclerView = findViewById((R.id.precycler_View))
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))

        val sessionManager =
            ParentSessionManager(this@Parent_DashBoard, ParentSessionManager.PSESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        fullName = userDetails[ParentSessionManager.PKEY_NAME]
        Phone_No = userDetails[ParentSessionManager.PKEY_PHONE_NO]
        email = userDetails[ParentSessionManager.PKEY_EMAIL]
        hAddress = userDetails[ParentSessionManager.PKEY_HADDRESS]

        val holder = ArrayList<ChildModel>()


        //Database Query
        val checkUser: Query =
            FirebaseDatabase.getInstance().getReference("Children").child(Phone_No!!)
        checkUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (childDS in snapshot.children) {

                    //progressBar!!.visibility = View.VISIBLE
                    val cm = ChildModel()
                    var obj = childDS.getValue(StudentDBHelperClass::class.java)
                    cm.setImg(R.drawable.profile)
                    cm.setHeader(obj!!.fullName)
                    cm.setDesc("Click to view the Child DashBoard.")
                    holder.add(cm)
                    adapter = ChildAdapterClass(holder)
                    recyclerView!!.setAdapter(adapter)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Parent_DashBoard, error.message, Toast.LENGTH_SHORT).show();
            }
        })

    }


    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    fun callAddChild(view: View) {
        val intent = Intent(applicationContext, ChildAdd1::class.java)
        startActivity(intent)
    }


}