package com.example.moksha

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.ArrayAdapter
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class signupPage : AppCompatActivity() {

    private lateinit var passwordEditText: EditText
    private var isPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)

        findViewById<TextView>(R.id.gotoSignIn).setOnClickListener {
            startActivity(Intent(this,loginPage::class.java))
            finish()
        }
        findViewById<ImageView>(R.id.goBackImage).setOnClickListener {
            startActivity(Intent(this,beginOptionPage::class.java))
            finish()
        }

        passwordEditText = findViewById<EditText>(R.id.password)

        passwordEditText.setOnTouchListener { _, event ->
            val DRAWABLE_END = 2
            if (event.action == android.view.MotionEvent.ACTION_UP) {
                if (event.rawX >= (passwordEditText.right - passwordEditText.compoundDrawables[DRAWABLE_END].bounds.width())) {
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


        val googleSignInButton: CardView = findViewById(R.id.googleSignUp)
        googleSignInButton.setOnClickListener {

        }




        //Registering the User
        val register:Button = findViewById(R.id.register)
        register.setOnClickListener {

            val email:String = findViewById<EditText>(R.id.email).text.toString()
            val password:String = findViewById<EditText>(R.id.password).text.toString()

            var ref = 0


            if(email == ""){
                ref = 1
                findViewById<EditText>(R.id.email).setBackgroundResource(R.drawable.wrongpassword)
            }
            if(password == ""){
                ref = 1
                findViewById<EditText>(R.id.password).setBackgroundResource(R.drawable.wrongpassword)
            }

            if(ref == 1){
                Toast.makeText(this,"Please fill up all the fields to proceed",Toast.LENGTH_SHORT).show()
            }


            if(ref == 0){

                //Start Registration from here
                //registerUser(name,email,gender,password)
                signup()
                startActivity(Intent(this,getStarted::class.java))
                finish()
            }
        }

    }


    private fun signup() {
        val username = findViewById<EditText>(R.id.username).text.toString()
        val email = findViewById<EditText>(R.id.email).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()

        val signupRequest = SignupRequest(username, email, password)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.31.124.75:8000/") // Replace with your local IP address
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.signup(signupRequest).enqueue(object : Callback<SignupResponse> {
            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@signupPage, "Signup successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@signupPage, "Signup failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                Toast.makeText(this@signupPage, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.d(TAG,"Error :- ${t.message}")
            }
        })
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.show_password_resized, 0)
        } else {
            passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.show_password_resized, 0)
        }
        isPasswordVisible = !isPasswordVisible
        passwordEditText.setSelection(passwordEditText.text.length)  // Move cursor to the end of text
    }
}

interface ApiService {
    @POST("api/users/signup/")
    fun signup(@Body request: SignupRequest): Call<SignupResponse>
}

data class SignupRequest(
    val username: String,
    val email: String,
    val password: String
)

data class SignupResponse(
    val message: String // Modify according to your response
    // Add other fields if necessary
)
