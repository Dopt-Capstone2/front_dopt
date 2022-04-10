package com.example.dopt_app.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.dopt_app.R
import com.google.android.material.textfield.TextInputEditText

class JoinActivity : AppCompatActivity() {

    private val TAG = "JoinActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener{
            val email= findViewById<TextInputEditText>(R.id.join_email_jo)
            val pwd = findViewById<TextInputEditText>(R.id.join_pw_jo)

            Log.d(TAG, email.text.toString())
            Log.d(TAG, pwd.text.toString())

        }

    }
}