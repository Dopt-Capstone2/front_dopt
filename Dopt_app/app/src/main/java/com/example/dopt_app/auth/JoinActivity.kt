package com.example.dopt_app.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.Monthly_Statistics
import com.example.dopt_app.data.JoinModel
import com.example.dopt_app.data.JoinResult
import com.example.dopt_app.shelter.ShelterMainActivity
import com.example.dopt_app.shelter.ShelterNameActivity
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
//            //사용자 회원가입하기
//            //파라미터로 사용자의 정보를 데이터클래스 객체로 만들어 넣는다
//            val userEmail= findViewById<TextInputEditText>(R.id.join_email_jo)
//            val userPw = findViewById<TextInputEditText>(R.id.join_pw_jo)
//            val data = User_Signup(userEmail.text.toString(), userPw.text.toString(), "hmin","Ilsan","nick")
            startActivity(intent)
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
            //사용자 정보 로딩하기
            //파라미터로 자신의 이메일을 넣는다
            RetrofitClient.Monthly_Statistics__instance.GET_Monthly_Statistics()
                .enqueue(object: Callback<Monthly_Statistics> {
                    override fun onFailure(call: Call<Monthly_Statistics>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<Monthly_Statistics>, response: Response<Monthly_Statistics>) {
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )

//            //북마크 정보 불러오기
//            //북마크들의 배열을 호출한다
//            //파라미터로 사용자의 이메일을 입력한다
//
//            //데이터 클래스로 저장은 아래처럼 합니다.
//            //정보 저장 객체
//            var bookmarkResponse = MutableLiveData<Bookmark_List>()
//            RetrofitClient.Bookmark_instance.GET_Bookmark("123@123")
//                .enqueue(object: Callback <Bookmark_List> {
//                    override fun onFailure(call: Call<Bookmark_List>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "failed")
//                        Log.d(TAG, t.message.toString())
//                    }
//                    override fun onResponse(call: Call<Bookmark_List>, response: Response<Bookmark_List>) {
//                        Toast.makeText(
//                            applicationContext,
//                            response.body().toString(),
//                            Toast.LENGTH_LONG
//                        ).show()
//                        Log.d(TAG, "succeeded")
//                        Log.d(TAG, response.body().toString())
//
//                        //지정한 데이터 클래스 객체로 저장
//                        bookmarkResponse.value = response.body() as Bookmark_List
//                        // 값을 복사
//                        val bookmarkRaw = bookmarkResponse.value?.copy()
//                        // 데이터 클래스들의 배열 출력
//                        Log.d("bookmarkRaw", bookmarkRaw.toString())
//                        //요소별 접근
//                        //response의 각 데이터 클래스 접근
//                        if (bookmarkRaw != null) {
//                            Log.d("bookmark", bookmarkRaw.Bookmark[0].toString())
//                        }
//                    }
//                }
//                )
        }

        val shelterJoinBtn = findViewById<Button>(R.id.shelterJoinBtn)
        shelterJoinBtn.setOnClickListener{
            val intent = Intent(this, ShelterNameActivity::class.java)
            startActivity(intent)
        }

    }
}