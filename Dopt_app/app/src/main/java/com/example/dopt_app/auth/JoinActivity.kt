package com.example.dopt_app.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.NicknameActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.AnimalOpenAPI
import com.example.dopt_app.data.OpenAnimal
import com.example.dopt_app.data.UserJoinAPI
import com.example.dopt_app.data.User_Signup
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinActivity : AppCompatActivity() {

    private val TAG = "JoinActivity"

    private fun postUserSignupData() {
        val call = UserJoinAPI.UserJoinRetrofitClient.userJoinService
        val dummy = User_Signup("nexlice_test", "nexlice_pw", "nexlice_test", "seoul", "nexlice")
        call.postLogin(dummy).enqueue(object:
            Callback<OpenAnimal> {
            override fun onResponse(call: Call<OpenAnimal>, response: Response<OpenAnimal>){
                animalResponse.value = response.body() as OpenAnimal
                //객체에 저장이 되지 않고 null만 출력해대서
                //isSuccessful()을 달아주었더니 잘 나온다.
                if (response.isSuccessful){
                    //val (animalBody, animalHeader) = animalResponse1.value
                    //Log.d("body", animalList.toString())
                    Log.d("success", "\n\n"+response.body().toString())
                }
                else{
                    Log.d("request_error", ""+response.errorBody())
                }

            }
            override fun onFailure(call: Call<OpenAnimal>, t : Throwable) {
                t.printStackTrace()
                Log.d("Failed", "Failed")
            }
        })
        return animalResponse
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener{
            val email= findViewById<TextInputEditText>(R.id.join_email_jo)
            val pwd = findViewById<TextInputEditText>(R.id.join_pw_jo)
            val intent= Intent(this, NicknameActivity::class.java)
            startActivity(intent)

            Log.d(TAG, email.text.toString())
            Log.d(TAG, pwd.text.toString())

        }



    }
}