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
import com.example.dopt_app.databinding.ActivityPreferageBinding
import com.example.dopt_app.databinding.ActivityPrefercolorBinding
import com.example.dopt_app.popup.PopupProfileDoneActivity
import kotlinx.android.synthetic.main.activity_prefer.*
import kotlinx.android.synthetic.main.activity_prefer.dogPrefer_kind_sp
import kotlinx.android.synthetic.main.activity_preferage.*
import kotlinx.android.synthetic.main.activity_preferbread.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var preferAge : String = "1"

// 프로필 입력 순서
// 닉네임 - 종 - 품종 - 성별 - 색 - 크기
class PreferAgeActivity : AppCompatActivity() {

    lateinit var binding: ActivityPreferageBinding

    private val TAG = "PreferAgeActivity"
    private lateinit var age : String

//    private lateinit var emailInfo : String
//    private lateinit var preferKind : String
//    private lateinit var preferBreed : String
//    private lateinit var preferGender : String
//    private lateinit var preferColor : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val agePreviousBtn = findViewById<TextView>(R.id.age_previous_btn)
        val ageNextBtn = findViewById<TextView>(R.id.age_next_btn)
        ageSpinner()

        ageNextBtn.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
//            intent.hasExtra("userEmail")
//            intent.hasExtra("kind")
//
//            emailInfo = intent.getStringExtra("userEmail").toString()
//            preferKind = intent.getStringExtra("kind").toString()
//            preferBreed = if (intent.hasExtra("preferBreed")){
//                intent.getStringExtra("preferBreed").toString()
//            }else{
//                "기타"
//            }
//            preferGender=intent.getStringExtra("preferGender").toString()
//            preferColor=intent.getStringExtra("preferColor").toString()

            Log.d(TAG+"막", emailInfo)
            Log.d(TAG+"막", preferKind)
            Log.d(TAG+"막", preferBreed)
            Log.d(TAG+"막", preferGender)
            Log.d(TAG+"막", preferColor)
            Log.d(TAG+"막", preferAge)

            //Preference UPDATE
            //name과 userEmail을 primary key로 받아 모든 정보를 수정
            //careNm은 사용하지 않으나 데이터 형식상 필요
//            val UPDATE_Preference_Data = Preference(emailInfo, emailInfo, preferKind, age, preferGender, preferColor, preferBreed)
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
//                        intent.putExtra("preferAge",age)
//                        startActivity(intent)
//                    }
//                })

//            Prefernece 정보 POST
            val POST_Preference_Data = Preference(emailInfo, emailInfo, preferKind, age, preferGender, preferColor, preferBreed)
            RetrofitClient.Preference_instance.POST_Preference(POST_Preference_Data)
                .enqueue(object: Callback <PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post P failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "Post P succeeded")
                        Log.d(TAG, response.body().toString())
                        intent.putExtra("preferAge",age)
                        startActivity(intent)
                    }
                }
                )

        }

        agePreviousBtn.setOnClickListener{
            val intent= Intent(this, PrefercolorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun ageSpinner(){
        val typeSpinner = dog_age_sp.findViewById<Spinner>(R.id.dog_age_sp)
        typeSpinner.adapter= ArrayAdapter.createFromResource(dog_age_sp.context, R.array.breed_age ,R.layout.item_spinner)

        typeSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                age  = typeSpinner.selectedItem.toString()
                preferAge=age
                Log.d(TAG+"나이", age)
                Log.d(TAG+"=나이", preferAge)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }
}