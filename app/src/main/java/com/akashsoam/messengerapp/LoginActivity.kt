package com.akashsoam.messengerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var loginBtn: Button

    private lateinit var emailEdtTxt: EditText
    private lateinit var passwordEdtTxt: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.btn_login)
        emailEdtTxt = findViewById(R.id.email_login)
        passwordEdtTxt = findViewById(R.id.password_login)

        var toolbarMain = findViewById<Toolbar>(R.id.toolbar_login)
        setSupportActionBar(toolbarMain)
        supportActionBar!!.title = "Login"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbarMain.setNavigationOnClickListener {

            val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        mAuth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            loginUser()
        }

    }

    private fun loginUser() {
        val email: String = emailEdtTxt.text.toString()
        val password: String = passwordEdtTxt.text.toString()
        if (email == "") {
            Toast.makeText(this, "enter email", Toast.LENGTH_SHORT).show()
        } else if (password == "") {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show()
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}