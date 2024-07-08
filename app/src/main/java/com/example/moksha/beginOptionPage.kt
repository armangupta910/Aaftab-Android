package com.example.moksha

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class beginOptionPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_begin_option_page)

        findViewById<Button>(R.id.login).setOnClickListener {
            startActivity(Intent(this,loginPage::class.java))
        }
        findViewById<Button>(R.id.register).setOnClickListener {
            startActivity(Intent(this,signupPage::class.java))
        }
        findViewById<TextView>(R.id.skip).setOnClickListener {
            startActivity(Intent(this,getStarted::class.java))
            finish()
        }

    }
}