package com.example.dopt_app.auth

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Response
import com.example.dopt_app.data.User_Signup
import com.example.dopt_app.shelter.ShelterMainActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import javax.security.auth.callback.Callback

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            val userEmail= findViewById<TextInputEditText>(R.id.login_email_jo).text.toString()
            val userPw = findViewById<TextInputEditText>(R.id.login_pw_jo).text.toString()
            val data = User_Signup(userEmail, userPw, "nick","nick","nick")
            Log.d(TAG, "clicked login btn!!!")

            //사용자 정보 GET
            //파라미터로 자신의 이메일을 넣는다
            RetrofitClient.User_Signup_instance.GET_User_Signup(userEmail)
                .enqueue(object: Callback <User_Signup> {
                    override fun onFailure(call: Call<User_Signup>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "GET U failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<User_Signup>, response: Response<User_Signup>) {
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "GET U succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )

            val userNm = ""
            val userLoc = ""
            // val data = User_Signup(userEmail, userPw, "nick",userLoc,userNm)
            startActivity(intent)

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
        }

        val shelter_loginBtn = findViewById<Button>(R.id.shelter_loginBtn)
        shelter_loginBtn.setOnClickListener{
            val intent = Intent(this, ShelterMainActivity::class.java)
//            val userEmail= findViewById<TextInputEditText>(R.id.login_email_jo)
//            val userPw = findViewById<TextInputEditText>(R.id.login_pw_jo)
//            val data = User_Signup(userEmail.text.toString(), userPw.text.toString(), "hmin","Ilsan","nick")
            startActivity(intent)

//            Log.d(TAG, "clicked login btn!!!")
//            Log.d(TAG, data.toString())
//            RetrofitClient.Shelter_Signup_instance.POST_User_SignUp(data)
//                .enqueue(object: Callback<PostResult> {
//                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                        // Log.d(TAG, "Request Failed start")
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "failed")
//                        Log.d(TAG, t.message.toString())
//                        // Log.d(TAG, "Request Failed end")
//                    }
//                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
//                        // Log.d(TAG,"Request successful start")
//                        // Log.d(TAG, response.body().toString())
//                        // Log.d(TAG, "Request successful end")
//                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "successed")
//                        Log.d(TAG, response.body().toString())
//                    }
//                }
//            )
        }

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        val shelterJoinBtn = findViewById<Button>(R.id.shelterJoinBtn)
        shelterJoinBtn.setOnClickListener{
            val intent = Intent(this, ShelterJoinActivity::class.java)
            startActivity(intent)
        }
    }
}