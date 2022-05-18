package com.example.dopt_app.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.User_Signup
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
            val data = User_Signup(userEmail.text.toString(), userPw.text.toString(), "hmin","Ilsan","nick")
            startActivity(intent)
            Log.d(TAG, "clicked join btn!!!")
            Log.d(TAG, data.toString())
            RetrofitClient.User_Signup_instance.POST_User_SignUp(data)
                .enqueue(object: Callback<PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        // Log.d(TAG, "Request Failed start")
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "failed")
                        Log.d(TAG, t.message.toString())
                        // Log.d(TAG, "Request Failed end")
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        // Log.d(TAG,"Request successful start")
                        // Log.d(TAG, response.body().toString())
                        // Log.d(TAG, "Request successful end")
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "successed")
                        Log.d(TAG, response.body().toString())
                    }
                }
            )

            RetrofitClient.User_Signup_instance.GET_User_Signup("test")
                .enqueue(object: Callback <User_Signup> {
                    override fun onFailure(call: Call<User_Signup>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<User_Signup>, response: Response<User_Signup>) {
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "successed")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )
        }
    }
}