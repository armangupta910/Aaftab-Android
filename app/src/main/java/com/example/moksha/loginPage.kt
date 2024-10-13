package com.example.moksha

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class loginPage : AppCompatActivity() {
    private var isPasswordVisible: Boolean = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        findViewById<TextView>(R.id.gotoSignUp).setOnClickListener {
            startActivity(Intent(this,signupPage::class.java))
            finish()
        }

        findViewById<ImageView>(R.id.goBackImage).setOnClickListener {
            startActivity(Intent(this,beginOptionPage::class.java))
            finish()
        }

        findViewById<EditText>(R.id.password).setOnTouchListener { _, event ->
            val DRAWABLE_END = 2
            if (event.action == android.view.MotionEvent.ACTION_UP) {
                if (event.rawX >= (findViewById<EditText>(R.id.password).right - findViewById<EditText>(R.id.password).compoundDrawables[DRAWABLE_END].bounds.width())) {
                    togglePasswordVisibility()
                    return@setOnTouchListener true
                }
            }
            false
        }

        findViewById<EditText>(R.id.email).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called to notify you that the text within start to start + before
                // is about to be replaced with new text with length after.
                // You can perform operations before text changes here if needed.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called to notify you that somewhere within s, the text has been changed.
                // Perform operations as the text is being changed.
                findViewById<EditText>(R.id.email).setBackgroundResource(R.drawable.roundbuttonbglogin)
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called to notify you that somewhere within s, the text has been changed.
                // Perform operations after the text has been changed if needed.
            }
        })

        findViewById<EditText>(R.id.password).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called to notify you that the text within start to start + before
                // is about to be replaced with new text with length after.
                // You can perform operations before text changes here if needed.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called to notify you that somewhere within s, the text has been changed.
                // Perform operations as the text is being changed.
                findViewById<EditText>(R.id.password).setBackgroundResource(R.drawable.roundbuttonbglogin)
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called to notify you that somewhere within s, the text has been changed.
                // Perform operations after the text has been changed if needed.
            }
        })

        //Registering the User
        val signIn: Button = findViewById(R.id.signin)
        signIn.setOnClickListener {
            //Log In Logic goes here
            startActivity(Intent(this,getStarted::class.java))
            finish()
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            findViewById<EditText>(R.id.password).transformationMethod = PasswordTransformationMethod.getInstance()
            findViewById<EditText>(R.id.password).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.show_password_resized, 0)
        } else {
            findViewById<EditText>(R.id.password).transformationMethod = HideReturnsTransformationMethod.getInstance()
            findViewById<EditText>(R.id.password).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.show_password_resized, 0)
        }
        isPasswordVisible = !isPasswordVisible
        findViewById<EditText>(R.id.password).setSelection(findViewById<EditText>(R.id.password).text.length)  // Move cursor to the end of text
    }
}