package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Preference
import com.example.dopt_app.databinding.ActivityPrefergenderBinding
import kotlinx.android.synthetic.main.activity_prefer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
var preferGender : String = "M"

class PrefergenderActivity : AppCompatActivity(){
    private val TAG = "PrefergenderBinding"
    lateinit var binding: ActivityPrefergenderBinding
    private var gender : String = "M"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrefergenderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val genderPreviousBtn = findViewById<TextView>(R.id.gender_previous_btn)
        val genderNextBtn = findViewById<TextView>(R.id.gender_next_btn)

        val genderMonBtn = findViewById<ImageView>(R.id.gender_Mon_btn)
        val genderFoffBtn = findViewById<ImageView>(R.id.gender_Foff_btn)


        genderMonBtn.setOnClickListener {
            gender = "M"
            binding.genderMonBtn.setImageResource(R.drawable.m_round_on)
            binding.genderFoffBtn.setImageResource(R.drawable.f_round_off)
            Log.d(TAG+"남자", gender)
            preferGender=gender
            Log.d(TAG, preferGender)
        }
        genderFoffBtn.setOnClickListener  {
            gender = "F"
            binding.genderMonBtn.setImageResource(R.drawable.m_round_off)
            binding.genderFoffBtn.setImageResource(R.drawable.f_round_on)
            Log.d(TAG+"여자", gender)
            preferGender=gender
            Log.d(TAG, preferGender)
        }

        genderNextBtn.setOnClickListener {
            val intent = Intent(this, PrefercolorActivity::class.java)
//            intent.hasExtra("userEmail")
//            intent.hasExtra("preferKind")
//            intent.hasExtra("preferBreed")
//
//            emailInfo = intent.getStringExtra("userEmail").toString()
//            preferKind = intent.getStringExtra("kind").toString()
//            preferBreed = intent.getStringExtra("preferBreed").toString()
//                if (intent.hasExtra("breed")){
//                intent.getStringExtra("preferBreed").toString()
//            }else{
//                "기타"
//            }
            //Preference UPDATE
            //name과 userEmail을 primary key로 받아 모든 정보를 수정
            //careNm은 사용하지 않으나 데이터 형식상 필요
//            val UPDATE_Preference_Data = Preference("선호도 정보", emailInfo, preferKind, "2022년 생", gender, "color", preferBreed)
//            RetrofitClient.Preference_instance.UPDATE_Preference(UPDATE_Preference_Data)
//                .enqueue(object: Callback<PostResult> {
//                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "UPDATE P failed")
//                        Log.d(TAG, t.message.toString())
//                    }
//                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
//                        Toast.makeText(
//                            applicationContext,
//                            response.body().toString(),
//                            Toast.LENGTH_LONG
//                        ).show()
//                        Log.d(TAG, "UPDATE P succeeded")
//                        Log.d(TAG, response.body().toString())
//                        intent.putExtra("preferGender",gender)
//
//                        startActivity(intent)
//                    }
//                })
            intent.putExtra("preferGender",gender)

            startActivity(intent)
        }

        genderPreviousBtn.setOnClickListener {

            val intent=Intent(this, PreferbreadActivity::class.java)
//            val intent = if (preferBreed == "기타"){
//                Intent(this, PreferActivity::class.java)
//            }else{
//                Intent(this, PreferbreadActivity::class.java)
//            }
            startActivity(intent)
        }

    }

}