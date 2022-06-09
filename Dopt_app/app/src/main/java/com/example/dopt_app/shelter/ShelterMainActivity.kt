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


                    //ShelterAPi
                    //Shelter에서 쓸 수 있는 기능들
                    //0. 북마크 정보 GET
                    //내 보호소 동물을 사용자가 북마크했는지 동물정보 GET
                    //북마크들의 배열을 호출한다
                    //파라미터로 shelterNm을 입력한다
                    //북마크의 careNm과 shelterNm이 일치하면 GET

                    var GET_S_bookmark_Response = MutableLiveData<Bookmark_List>()
                    RetrofitClient.Shelter_instance.GET_Bookmark("한국동물구조관리협회")
                        .enqueue(object: Callback <Bookmark_List> {
                            override fun onFailure(call: Call<Bookmark_List>, t: Throwable) {
//                                Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                                Log.d(TAG, "GET S_B failed")
                                Log.d(TAG, t.message.toString())
                            }
                            override fun onResponse(call: Call<Bookmark_List>, response: Response<Bookmark_List>) {
//                                Toast.makeText(
//                                    applicationContext,
//                                    response.body().toString(),
//                                    Toast.LENGTH_LONG
//                                ).show()
                                Log.d(TAG, "GET S_B succeeded")
                                Log.d(TAG, response.body().toString())

                                //지정한 데이터 클래스 객체로 저장
                                GET_S_bookmark_Response.value = response.body() as Bookmark_List
                                // 값을 복사
                                val GET_S_bookmarkRaw = GET_S_bookmark_Response.value?.copy()
                                // 데이터 클래스들의 배열 출력
                                Log.d("GET_S_bookmarkRaw", GET_S_bookmarkRaw.toString())
                                //요소별 접근
                                //response의 각 데이터 클래스 접근
                                if (GET_S_bookmarkRaw != null) {
                                    Log.d("GET_bookmark", GET_S_bookmarkRaw.Bookmark[0].toString())

                                    for (element in GET_S_bookmarkRaw.Bookmark){
                                        Log.d("GET_bookmark size", GET_S_bookmarkRaw.Bookmark.size.toString())
                                        shelterItems.removeAll(listOf(element))
                                        Log.d(TAG+"비었니?", shelterItems.toString())
                                        shelterItems.add(element)
                                        Log.d(TAG+"차있니?", shelterItems.toString())
                                        supportFragmentManager.beginTransaction()
                                            .replace(R.id.shelter_main_frm, ShelterHomeFragment())
                                            .commitAllowingStateLoss()

                                    }

                                }
                            }
                        }
                        )

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