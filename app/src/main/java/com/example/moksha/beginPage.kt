package com.example.moksha

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class beginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_begin_page)

        findViewById<ImageView>(R.id.nextPage).setOnClickListener {
            startActivity(Intent(this,beginOptionPage::class.java))
            finish()
        }

    }
}