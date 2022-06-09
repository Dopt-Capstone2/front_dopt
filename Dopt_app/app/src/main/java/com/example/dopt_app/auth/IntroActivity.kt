package com.example.dopt_app.auth

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Preference_List
import com.example.dopt_app.data.User_Signup
import com.example.dopt_app.shelter.ShelterMainActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET


class IntroActivity : AppCompatActivity() {
    private val TAG = "IntroActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener{
            val emailIntent = Intent(this, MainActivity::class.java)
            val userEmail= findViewById<TextInputEditText>(R.id.login_email_jo).text.toString()
            val userPw = findViewById<TextInputEditText>(R.id.login_pw_jo).text.toString()
            startActivity(emailIntent)
            Log.d(TAG, "clicked login btn!!!")
            var GET_user_Response = MutableLiveData<User_Signup>()
//            사용자 정보 GET
//            파라미터로 자신의 이메일을 넣는다
            RetrofitClient.User_Signup_instance.GET_User_Signup(userEmail)
                .enqueue(object: Callback <User_Signup> {
                    override fun onFailure(call: Call<User_Signup>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "GET U failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<User_Signup>, response: Response<User_Signup>) {
//                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "GET U succeeded")
                        Log.d(TAG, response.body().toString())

                        GET_user_Response.value=response.body() as User_Signup
                        val GET_user_UserRaw = GET_user_Response.value?.copy()
                        Log.d("user_UserRaw", GET_user_UserRaw.toString())
                        if (GET_user_UserRaw?.userPw == userPw){
                            Toast.makeText(applicationContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                            emailInfo = userEmail
                            Log.d(TAG+"메일은?", emailInfo)
                            startActivity(emailIntent)

                        }else{
                            Toast.makeText(applicationContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
                })

            //Preference 정보 GET
            //사용자가 등록한 preference의 list를 get합니다.
            var GET_Preference_Response = MutableLiveData<Preference_List>()
            RetrofitClient.Preference_instance.GET_Preference(userEmail)
                .enqueue(object: Callback <Preference_List> {
                    override fun onFailure(call: Call<Preference_List>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Get P failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<Preference_List>, response: Response<Preference_List>) {
//                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Get P succeeded")
                        Log.d(TAG, response.body().toString())

                        //지정한 데이터 클래스 객체로 저장
                        GET_Preference_Response.value = response.body() as Preference_List
                        // 값을 복사
                        val GET_PreferenceRaw = GET_Preference_Response.value?.copy()
                        // 데이터 클래스들의 배열 출력
                        Log.d("GET_PreferenceRaw", GET_PreferenceRaw.toString())
                        //요소별 접근
                        //response의 각 데이터 클래스 접근
                        if (GET_PreferenceRaw != null) {
                            Log.d("Preference", GET_PreferenceRaw.Preference[0].toString())
                            emailInfo = GET_PreferenceRaw.Preference[0].userEmail
                            preferKind = GET_PreferenceRaw.Preference[0].type
                            preferBreed = GET_PreferenceRaw.Preference[0].breed
                            preferGender = GET_PreferenceRaw.Preference[0].sex
                            preferColor = GET_PreferenceRaw.Preference[0].color
                            preferAge = GET_PreferenceRaw.Preference[0].age

                            Log.d(TAG+"막", emailInfo)
                            Log.d(TAG+"막", preferKind)
                            Log.d(TAG+"막", preferBreed)
                            Log.d(TAG+"막", preferGender)
                            Log.d(TAG+"막", preferColor)
                            Log.d(TAG+"막", preferAge)
                        }
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