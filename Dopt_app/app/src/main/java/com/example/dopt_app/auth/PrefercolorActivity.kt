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
import com.example.dopt_app.databinding.ActivityPreferBinding
import com.example.dopt_app.databinding.ActivityPrefercolorBinding
import kotlinx.android.synthetic.main.activity_prefer.*
import kotlinx.android.synthetic.main.activity_prefercolor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var preferColor : String = "검정"

class PrefercolorActivity : AppCompatActivity(){

    private val TAG = "PrefercolorActivity"
    lateinit var binding: ActivityPrefercolorBinding
    private lateinit var color : String

//    private lateinit var emailInfo : String
//    private lateinit var preferKind : String
//    private lateinit var preferBreed : String
//    private lateinit var preferGender : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrefercolorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val colorPreviousBtn = findViewById<TextView>(R.id.color_previous_btn)
        val colorNextBtn = findViewById<TextView>(R.id.color_next_btn)
        breedSpinner()

        colorNextBtn.setOnClickListener {
            val intent = Intent(this, PreferAgeActivity::class.java)
//            intent.hasExtra("userEmail")
//            intent.hasExtra("kind")
//
//            emailInfo = intent.getStringExtra("userEmail").toString()
//            preferKind = intent.getStringExtra("kind").toString()
//
//            preferBreed = if (intent.hasExtra("preferBreed")){
//                intent.getStringExtra("preferBreed").toString()
//            }else{
//                "기타"
//            }
//
//            preferGender=intent.getStringExtra("preferGender").toString()

            Log.d(TAG, emailInfo)
            Log.d(TAG, preferKind)
            Log.d(TAG, preferBreed)
            Log.d(TAG, preferGender)

            //Preference UPDATE
            //name과 userEmail을 primary key로 받아 모든 정보를 수정
            //careNm은 사용하지 않으나 데이터 형식상 필요
//            val UPDATE_Preference_Data = Preference("선호도 정보", emailInfo, preferKind, "2022년 생", preferGender, color, preferBreed)
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
//                        intent.putExtra("preferColor",color)
//                        startActivity(intent)
//                    }
//                })
            intent.putExtra("preferColor",color)
            startActivity(intent)
        }

        colorPreviousBtn.setOnClickListener {
            val intent=Intent(this, PrefergenderActivity::class.java)
            startActivity(intent)
        }
    }

    private fun breedSpinner(){
        val typeSpinner = dog_color_sp.findViewById<Spinner>(R.id.dog_color_sp)
        typeSpinner.adapter= ArrayAdapter.createFromResource(dog_color_sp.context, R.array.bread_color,R.layout.item_spinner)

        typeSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                color  = typeSpinner.selectedItem.toString()
                preferColor = color
                Log.d(TAG, preferColor)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }
}