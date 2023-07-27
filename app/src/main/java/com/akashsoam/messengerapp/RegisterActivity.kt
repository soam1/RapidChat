package com.akashsoam.messengerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerButton: Button
    private lateinit var usernameEdtTxt: EditText
    private lateinit var emailEdtTxt: EditText
    private lateinit var passwordEdtTxt: EditText

    private var firebaseUserId: String = ""
    private lateinit var mAuth: FirebaseAuth

    private lateinit var refUsers: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton = findViewById(R.id.btn_register)
        usernameEdtTxt = findViewById(R.id.username_register)
        emailEdtTxt = findViewById(R.id.email_register)
        passwordEdtTxt = findViewById(R.id.password_register)

        var toolbarMain = findViewById<Toolbar>(R.id.toolbar_register)
        setSupportActionBar(toolbarMain)
        supportActionBar!!.title = "Register"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbarMain.setNavigationOnClickListener {

            val intent = Intent(this@RegisterActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        mAuth = FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            registerUser()
        }

    }

    private fun registerUser() {
        val username: String = usernameEdtTxt.text.toString()
        val email: String = emailEdtTxt.text.toString()
        val password: String = passwordEdtTxt.text.toString()

        if (username == "") {
            Toast.makeText(this, "enter username", Toast.LENGTH_SHORT).show()
        } else if (email == "") {
            Toast.makeText(this, "enter email", Toast.LENGTH_SHORT).show()
        } else if (password == "") {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show()
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("users")
                        .child(firebaseUserId)

                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["username"] = username
                    userHashMap["profile"] = "gs://messengerapp-c8aca.appspot.com/profile_image.png"
                    userHashMap["cover"] = "gs://messengerapp-c8aca.appspot.com/cover_image.png"
                    userHashMap["status"] = "offline"
                    userHashMap["search"] = username.lowercase(Locale.ROOT)
                    userHashMap["facebook"] = "https://facebook.com/narendramodi"
                    userHashMap["instagram"] = "https://instagram.com/narendramodi"
                    userHashMap["website"] = "https://www.india.gov.in"

                    refUsers.updateChildren(userHashMap).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                            intent.addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            )
                            startActivity(intent)
                            finish()
                        }
                    }

                } else {
                    Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}