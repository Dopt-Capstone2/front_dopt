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
import com.example.dopt_app.api.ShelterAPI
import com.example.dopt_app.api.Shelter_SignupAPI
import com.example.dopt_app.data.Monthly_Statistics
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Shelter_Signup
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

    private val TAG = "ShelterJoinActivity"
    // private lateinit var shelterEmail : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter_join)

        val joinBtn = findViewById<Button>(R.id.shelterJoinBtn)
        joinBtn.setOnClickListener{
            val intent = Intent(this, ShelterMainActivity::class.java)


            //사용자 회원가입하기
            //파라미터로 사용자의 정보를 데이터클래스 객체로 만들어 넣는다
            val shelterEmail= findViewById<TextInputEditText>(R.id.shelter_join_email_jo).toString()
            val shelterPw = findViewById<TextInputEditText>(R.id.shelter_join_pw_jo)
            val shelterNm = findViewById<TextInputEditText>(R.id.join_shelterNm_jo)
            val phone = findViewById<TextInputEditText>(R.id.join_phone_jo)
            val shelterLoc = findViewById<TextInputEditText>(R.id.join_shelterLoc_jo)
            val data = Shelter_Signup(shelterEmail, shelterPw.text.toString(), shelterNm.text.toString(),phone.text.toString(),shelterLoc.text.toString(),"img","123")
            startActivity(intent)

            // 보호소 이메일 데이터 전달
            intent.putExtra("shelterEmail", shelterEmail)

            Log.d(TAG, "clicked join btn!!!")
            Log.d(TAG, data.toString())
            RetrofitClient.Shelter_Signup_instance.POST_Shelter_Signup(data)
                .enqueue(object: Callback<PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Log.d(TAG, "Request Failed start")
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "failed")
                        Log.d(TAG, t.message.toString())
                        Log.d(TAG, "Request Failed end")
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Log.d(TAG,"Request successful start")
                        Log.d(TAG, response.body().toString())
                        Log.d(TAG, "Request successful end")
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "successed")
                        Log.d(TAG, response.body().toString())
                    }
                }
            )

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