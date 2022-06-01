package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.api.RetrofitClient.Preference_instance
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Preference
import com.example.dopt_app.databinding.ActivityPreferBinding
import kotlinx.android.synthetic.main.activity_prefer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST

class PreferActivity : AppCompatActivity() {

    private val TAG = "PreferActivity"
    lateinit var binding: ActivityPreferBinding

    private lateinit var emailInfo : String
    private lateinit var kind : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.hasExtra("userEmail")
        emailInfo = intent.getStringExtra("userEmail").toString()
        Log.d(TAG, emailInfo)

        val dogbreedPreviousBtn = findViewById<TextView>(R.id.dogbreed_previous_btn)
        val dogbreedNextBtn = findViewById<TextView>(R.id.dogbreed_next_btn)
        preferSpinner()

        dogbreedNextBtn.setOnClickListener {
            val intent = if (kind == "기타"){
                Intent(this, PreferbreadActivity::class.java)
            }else{
                Intent(this, PrefercolorActivity::class.java)
            }
            //Prefernece 정보 POST
            val POST_Preference_Data = Preference("선호도 정보", emailInfo, kind, "2022년 생", "M", "하양색", "기타")
            RetrofitClient.Preference_instance.POST_Preference(POST_Preference_Data)
                .enqueue(object : Callback<PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post P failed")
                        Log.d(TAG, t.message.toString())
                    }

                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post P succeeded")
                        Log.d(TAG, response.body().toString())
                        startActivity(intent)
                    }
                })
        }

        dogbreedPreviousBtn.setOnClickListener {
            val intent = Intent(this, CheckListActivity::class.java)
            startActivity(intent)
        }

    }

    private fun preferSpinner(){
        val typeSpinner = dogPrefer_kind_sp.findViewById<Spinner>(R.id.dogPrefer_kind_sp)
        typeSpinner.adapter= ArrayAdapter.createFromResource(dogPrefer_kind_sp.context, R.array.kind,R.layout.item_spinner)

        typeSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                kind = typeSpinner.selectedItem.toString()
                startActivity(intent)
                intent.putExtra("preferKind",kind)

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }
}