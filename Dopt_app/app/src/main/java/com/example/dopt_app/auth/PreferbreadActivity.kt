package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.BaseActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Preference
import com.example.dopt_app.databinding.ActivityPreferBinding
import com.example.dopt_app.databinding.ActivityPreferbreadBinding
import kotlinx.android.synthetic.main.activity_prefer.*
import kotlinx.android.synthetic.main.activity_preferbread.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var preferBreed : String = "믹스견"

class PreferbreadActivity : AppCompatActivity() {

    private val TAG = "PreferbreadActivity"
    lateinit var binding: ActivityPreferbreadBinding
    private lateinit var breed : String

    // private var userEmail : String = "123@123"
    // private lateinit var preferKind : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferbreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        breedSpinner()

        val breedPreviousBtn = findViewById<TextView>(R.id.breed_previous_btn)
        val breedNextBtn = findViewById<TextView>(R.id.breed_next_btn)

        breedNextBtn.setOnClickListener {
            val intent = Intent(this, PrefergenderActivity::class.java)

            // intent.hasExtra("userEmail")
            // intent.hasExtra("kind")
            // userEmail = intent.getStringExtra("userEmail").toString()
            // preferKind = intent.getStringExtra("kind").toString()
            // Log.d(TAG, userEmail)
            // Log.d(TAG, preferKind)

            //Preference UPDATE
            //name과 userEmail을 primary key로 받아 모든 정보를 수정
            //careNm은 사용하지 않으나 데이터 형식상 필요
//            val UPDATE_Preference_Data = Preference("선호도 정보", emailInfo, preferKind, "2022년 생", "M", "하양색", breed)
//            RetrofitClient.Preference_instance.UPDATE_Preference(UPDATE_Preference_Data)
//                .enqueue(object: Callback <PostResult> {
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
//                        intent.putExtra("preferBreed",breed)
//
//                        startActivity(intent)
//                    }
//                })
            intent.putExtra("preferBreed",breed)

            startActivity(intent)
        }

        breedPreviousBtn.setOnClickListener {
            val intent = Intent(this, PreferActivity::class.java)
            startActivity(intent)
        }
    }

    private fun breedSpinner(){


        val breedSpinner = dog_prefer_breed_sp.findViewById<Spinner>(R.id.dog_prefer_breed_sp)

        breedSpinner.adapter= ArrayAdapter.createFromResource(dog_prefer_breed_sp.context, R.array.bread_dog,R.layout.item_spinner)
        Log.d(TAG+"왜?", preferBreed)
//        if(preferKind == "개"){
//            breedSpinner.adapter= ArrayAdapter.createFromResource(dogPrefer_kind_sp.context, R.array.bread_dog,R.layout.item_spinner)
//        }else if (preferKind == "고양이"){
//            breedSpinner.adapter= ArrayAdapter.createFromResource(dogPrefer_kind_sp.context, R.array.bread_cat,R.layout.item_spinner)
//        }

        breedSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                breed = breedSpinner.selectedItem.toString()
                Log.d(TAG, breed)
                preferBreed= breed
                Log.d(TAG, preferBreed)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }
}