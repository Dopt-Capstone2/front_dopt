package com.example.dopt_app.shelter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.R
import com.example.dopt_app.animalDatas
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.data.Bookmark_List
import com.example.dopt_app.data.DataX
import com.example.dopt_app.data.Shelter_Signup
import com.example.dopt_app.databinding.ActivityShelterMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

var shelterItems = ArrayList<Bookmark>()


class ShelterMainActivity : AppCompatActivity() {

    lateinit var binding: ActivityShelterMainBinding
    private val TAG = "ShelterMainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShelterMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initShelterNavigation()
    }

    private fun initShelterNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.shelter_main_frm, ShelterHomeFragment())
            .commitAllowingStateLoss()

        binding.shelterMainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.shelterShareFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.shelter_main_frm, ShelterShareFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.shelterHomeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.shelter_main_frm, ShelterHomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.shelterAccountFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.shelter_main_frm, ShelterAccountFragment())
                        .commitAllowingStateLoss()
                    //Shelter_Signup GET
                    //파라미터로 자신의 이메일을 넣는다
//                    var Shelter_Signup_instance = Shelter_Signup("123@123","123@123", "테스트", "서울특별시", "테스트","테스트","테스트")
//                    RetrofitClient.Shelter_Signup_instance.GET_Shelter_Signup("321@321")
//                        .enqueue(object: Callback <Shelter_Signup> {
//                            override fun onFailure(call: Call<Shelter_Signup>, t: Throwable) {
////                                Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
//                                Log.d(TAG, "GET S failed")
//                                Log.d(TAG, t.message.toString())
//                            }
//                            override fun onResponse(call: Call<Shelter_Signup>, response: Response<Shelter_Signup>) {
////                                Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
//                                Log.d(TAG, "GET S succeeded")
//                                Log.d(TAG, response.body().toString())
//                            }
//                        }
//                        )

                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}