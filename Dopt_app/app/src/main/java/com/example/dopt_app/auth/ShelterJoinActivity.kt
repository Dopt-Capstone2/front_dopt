package com.example.dopt_app.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.Monthly_Statistics
import com.example.dopt_app.data.User_Signup
// import com.example.dopt_app.data.JoinModel
// import com.example.dopt_app.data.JoinResult
import com.example.dopt_app.shelter.ShelterMainActivity
import com.example.dopt_app.shelter.ShelterNameActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShelterJoinActivity : AppCompatActivity() {

    private val TAG = "JoinActivity"
    private lateinit var userEmail : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter_join)

        val joinBtn = findViewById<Button>(R.id.shelter_loginBtn)
        joinBtn.setOnClickListener{
            val intent = Intent(this, ShelterMainActivity::class.java)


//            //사용자 회원가입하기
//            //파라미터로 사용자의 정보를 데이터클래스 객체로 만들어 넣는다
            userEmail= findViewById<TextInputEditText>(R.id.join_email_jo).toString()
            val userPw = findViewById<TextInputEditText>(R.id.join_pw_jo)
            val data = User_Signup(userEmail, userPw.text.toString(), "hmin","Ilsan","nick")
            startActivity(intent)
            intent.putExtra("userEmail",userEmail)
//            Log.d(TAG, "clicked join btn!!!")
//            Log.d(TAG, data.toString())
//            RetrofitClient.User_Signup_instance.POST_User_SignUp(data)
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

//            //사용자 정보 로딩하기
//            //파라미터로 자신의 이메일을 넣는다
//            RetrofitClient.User_Signup_instance.GET_User_Signup("123@123")
//                .enqueue(object: Callback <User_Signup> {
//                    override fun onFailure(call: Call<User_Signup>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "failed")
//                        Log.d(TAG, t.message.toString())
//                    }
//                    override fun onResponse(call: Call<User_Signup>, response: Response<User_Signup>) {
//                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "succeeded")
//                        Log.d(TAG, response.body().toString())
//                    }
//                }
//                )

        }

    }
}