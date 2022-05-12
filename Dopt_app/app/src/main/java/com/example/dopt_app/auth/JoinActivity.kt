package com.example.dopt_app.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class JoinActivity : AppCompatActivity() {

    private val TAG = "JoinActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener{
            val userEmail= findViewById<TextInputEditText>(R.id.join_email_jo)
            val userPw = findViewById<TextInputEditText>(R.id.join_pw_jo)
            //val intent= Intent(this, NicknameActivity::class.java)
            startActivity(intent)

            RetrofitClient.instance.userJoin(userEmail,userPw)
                .enqueue(object: Callback<Join> {
                    override fun onFailure(call: Call<Join>, t: Throwable) {
                        Log.d(TAG, "Request Failed")
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Request Failed end")
                    }
                    override fun onResponse(call: Call<Join>, response: Response<Join>) {
                        Log.d(TAG,"Request successful")
                        Log.d(TAG, response.body().toString())
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                    }
                }
                )

            Log.d(TAG, "auth/JoinActivity")
            Log.d(TAG, userEmail.text.toString())
            Log.d(TAG, userPw.text.toString())

        }
    }
}