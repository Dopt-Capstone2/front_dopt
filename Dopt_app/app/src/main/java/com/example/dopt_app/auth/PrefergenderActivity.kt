package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.BaseActivity
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Preference
import com.example.dopt_app.databinding.ActivityPrefergenderBinding
import kotlinx.android.synthetic.main.activity_prefergender.*
import javax.security.auth.callback.Callback
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class PrefergenderActivity : BaseActivity<ActivityPrefergenderBinding>(ActivityPrefergenderBinding::inflate) , View.OnClickListener{
    private lateinit var preferInfo : String
    private var itemSex : String = "0"

    override fun initAfterBinding() {

        binding.genderPreviousBtn.setOnClickListener(this)
        binding.genderNextBtn.setOnClickListener(this)
        binding.genderMBtn.setOnClickListener(this)
        binding.genderFBtn.setOnClickListener(this)
        intent?.let {
            it.getStringExtra("preferInfo")?.let{ content->
                preferInfo=content
            }
        }
    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.genderPreviousBtn -> startActivityWithClear(MainActivity::class.java)

            binding.genderMBtn ->
            {
                itemSex="0"
                binding.genderMBtn.setImageResource(R.drawable.m_on)
                binding.genderFBtn.setImageResource(R.drawable.f_off)

            }
            binding.genderFBtn -> {
                itemSex="1"
                binding.genderMBtn.setImageResource(R.drawable.m_off)
                binding.genderFBtn.setImageResource(R.drawable.f_on)


            }
            binding.genderNextBtn->dogInfo()


        }
    }

    private fun getDogInfo() : Preference {
        val token = preferInfo.split(",")
        var name : String = token[0]
        var userEmail : String = token[1]
        var age : String = token[2]
        var breed : String = token[3]
        var sex : String = token[4]
        var color : String = token[5]
        var type: String = token[6]

        Log.d("INFO_NAME",name)
        Log.d("INFO_EMAIL",userEmail)
        Log.d("INFO_ITEM_AGE",age)
        Log.d("INFO_ITEM_BREED",breed)
        Log.d("INFO_ITEM_SEX",sex)
        Log.d("INFO_ITEM_AGE",age)
        Log.d("INFO_ITEM_COLOR",color)
        Log.d("INFO_ITEM_TYPE",type)

        return Preference(name, userEmail,age, sex, age, color, type)

    }

    private fun dogInfo() {


        val data = Preference(
            getDogInfo().name,
            getDogInfo().userEmail,
            getDogInfo().breed,
            getDogInfo().age,
            getDogInfo().sex,
            getDogInfo().color,
            getDogInfo().type
        )

//        RetrofitClient.Preference_instance.POST_Preference(data)
//            .enqueue(object : Callback<PostResult> {
//                override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                }
//
//                override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
//                    Toast.makeText(
//                        applicationContext,
//                        response.body().toString(),
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//
//            })

//    override fun onDogInfoLoading() {
//
//    }
//    override fun onDogInfoSuccess(dogIdx: Dog)
//    {
//        Log.d("LOG_SUCCESS","성공")
//        startActivityWithClear(DogInfoCheckActivity::class.java)
//    }
//
//    override fun onDogInfoFailure(code: Int, message: String) {
//        when(code) {
//            6000 -> {
//                Toast.makeText(this, "강아지 정보가 정확하지 않습니다.", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }

    }}

