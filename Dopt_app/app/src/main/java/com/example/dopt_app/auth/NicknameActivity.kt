package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dopt_app.BaseActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Response
import com.example.dopt_app.data.User_Signup
import com.example.dopt_app.databinding.ActivityNicknameBinding
import com.example.dopt_app.popup.PopupProfileDoneActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_nickname.*
import kotlinx.android.synthetic.main.activity_preferbread.*
import javax.security.auth.callback.Callback

// 프로필 입력 순서
// 닉네임 - 종 - 품종 - 성별 - 색 - 크기
class NicknameActivity : AppCompatActivity() {
    lateinit var binding: ActivityNicknameBinding

    private val TAG = "NicknameActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)
        Glide.with(this)
            .load(R.drawable.nickname_cat_img)
            .into(binding.preferKindImg)

        val goProfileBtn = findViewById<Button>(R.id.go_profile_btn)
        goProfileBtn.setOnClickListener{
            val intent = Intent(this, PreferActivity::class.java)
            val userNm = findViewById<TextInputEditText>(R.id.join_nickname_jo)
            val data = User_Signup("123@123","test", userNm.text.toString(),"Ilsan","nick")
            startActivity(intent)
//            RetrofitClient.User_Signup_instance.UPDATE_User_Signup()
//            RetrofitClient.User_Signup_instance.POST_User_SignUp(data).enqueue(object: Callback<PostResult> {
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
//            })
        }

    }

}