package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.Checklist
import com.example.dopt_app.data.PostResult
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_checklist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckListActivity : AppCompatActivity(){

    private val TAG = "CheckListActivity"

    private var btn1 : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        // 사용자 이메일 값 받아오기
        val loginEmail = intent.getStringExtra("userEmail").toString()
//        if (intent.hasExtra("userEmail") ){
//            Log.d(TAG, loginEmail)

            val goPreferBtn = findViewById<Button>(R.id.go_prefer_btn)
            goPreferBtn.setOnClickListener{
                val intent = Intent(this, PreferActivity::class.java)
                startActivity(intent)
//                val POST_Checklist_Data = Checklist(loginEmail, 0,0,0,0,0,0,0,0,0,0,0,0)
//                RetrofitClient.Checklist_instance.POST_Checklist(POST_Checklist_Data)
//                .enqueue(object: Callback <PostResult> {
//                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "Post C failed")
//                        Log.d(TAG, t.message.toString())
//                    }
//                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
//                        Toast.makeText(
//                            applicationContext,
//                            response.body().toString(),
//                            Toast.LENGTH_LONG
//                        ).show()
//                        Log.d(TAG, "Post C succeeded")
//                        Log.d(TAG, response.body().toString())
//                    }
//                }
//                )
//
//            }
        }




    }

}
//Checklist POST
//            val POST_Checklist_Data = Checklist("123@123", 0,0,0,0,0,0,0,0,0,0,0,0)
//            RetrofitClient.Checklist_instance.POST_Checklist(POST_Checklist_Data)
//                .enqueue(object: Callback <PostResult> {
//                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "Post C failed")
//                        Log.d(TAG, t.message.toString())
//                    }
//                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
//                        Toast.makeText(
//                            applicationContext,
//                            response.body().toString(),
//                            Toast.LENGTH_LONG
//                        ).show()
//                        Log.d(TAG, "Post C succeeded")
//                        Log.d(TAG, response.body().toString())
//                    }
//                }
//                )