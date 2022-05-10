package com.example.dopt_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.data.Join
import com.example.dopt_app.databinding.ActivityJoinBinding
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinActivity: AppCompatActivity()  {
    lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener{
            val email = findViewById<TextInputEditText>(R.id.join_email_jo)
            val pw = findViewById<TextInputEditText>(R.id.join_pw_jo)

            Log.d(TAG, email.toString())
            Log.d(TAG, pw.toString())

        }
    }



}