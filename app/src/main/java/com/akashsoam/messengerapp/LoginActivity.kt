package com.akashsoam.messengerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var toolbarMain = findViewById<Toolbar>(R.id.toolbar_login)
        setSupportActionBar(toolbarMain)
        supportActionBar!!.title = "Login"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbarMain.setNavigationOnClickListener {

            val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
//        findViewById<Button>(R.id.btn_login).setOnClickListener {
//            val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }
}