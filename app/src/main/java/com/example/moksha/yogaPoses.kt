package com.example.moksha

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class yogaPoses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.yoga_poses)
        findViewById<Button>(R.id.pose1).setOnClickListener {
            startActivity(Intent(this,poseActivity::class.java))
        }
    }
}