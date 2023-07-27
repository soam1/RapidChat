package com.akashsoam.messengerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        var toolbarMain = findViewById<Toolbar>(R.id.toolbar_register)
        setSupportActionBar(toolbarMain)
        supportActionBar!!.title = "Register"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbarMain.setNavigationOnClickListener {

            val intent = Intent(this@RegisterActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }


//        findViewById<Button>(R.id.btn_register).setOnClickListener {
//            val intent = Intent(this@RegisterActivity, WelcomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }
}