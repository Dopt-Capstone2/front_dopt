package com.example.dopt_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.Join
import com.example.dopt_app.databinding.ActivityJoinBinding
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinActivity: AppCompatActivity()  {
    lateinit var binding: ActivityJoinBinding

    private var userEmail = ""
    private var userPw = ""
    // private var userNm = ""
    // private var userLoc = ""
    // private var nickNm = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener{
            val email = findViewById<TextInputEditText>(R.id.join_email_jo)
            val pw = findViewById<TextInputEditText>(R.id.join_pw_jo)

            //TODO : figma.com/file/5LtX7FfrvlOr4dJt0TaBz9/lip-reading?node-id=0%3A1
            // Email 입력되지 않은 경우 입력하라고 경고

            RetrofitClient.instance.userJoin(email,pw)
                .enqueue(object: Callback<Join>{
                    override fun onFailure(call: Call<Join>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<Join>, response: Response<Join>) {
                        Toast.makeText(applicationContext, response.body()?.mesage,Toast.LENGTH_LONG).show()
                    }
                }
            )
            Log.d(TAG, email.toString())
            Log.d(TAG, pw.toString())

        }
    }



}