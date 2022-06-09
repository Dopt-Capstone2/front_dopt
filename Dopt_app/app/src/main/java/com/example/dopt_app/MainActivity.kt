package com.example.dopt_app

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.account.AccountFragment
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.auth.*
import com.example.dopt_app.data.*
import com.example.dopt_app.databinding.ActivityMainBinding
import com.example.dopt_app.databinding.ActivityMatchBinding
import com.example.dopt_app.home.HomeFragment
import com.example.dopt_app.match.*
import com.example.dopt_app.match.MatchActivity
// import com.example.dopt_app.match.getAnimalData
import com.example.dopt_app.share.ShareFragment
import com.example.dopt_app.star.StarFragment
import kotlinx.android.synthetic.main.activity_prefer.*
import kotlinx.android.synthetic.main.fragment_match.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

val data = Preference("default1", "123@123", "개", "1", "M","흰색","개")
//data부분에 인자 넘기기 여기서 data는 preferece 데이터 클래스 객체
var cardItems = mutableListOf<DataX>()
var animalDatas = ArrayList<Bookmark>()


class MainActivity : AppCompatActivity() {

    lateinit var udpThread: Thread
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        udpThread=UdpTread()
        initBottomNavigation()

    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {

                    var Match_Response = MutableLiveData<Match>()

                    RetrofitClient.Match_instance.GET_Match(
                        emailInfo, emailInfo, preferKind, preferAge, preferGender, preferColor, preferBreed, "서울특별시")
                        .enqueue(object : Callback<Match> {
                            override fun onFailure(call: Call<Match>, t: Throwable) {
                                Log.d(ContentValues.TAG, "failed")
                                Log.d(ContentValues.TAG, t.message.toString()) }
                            override fun onResponse(call: Call<Match>, response: Response<Match>) {
                                Log.d(ContentValues.TAG, "succeeded")
                                Log.d(ContentValues.TAG, response.body().toString())
                                Match_Response.value = response.body() as Match
                                // 값을 복사
                                val Match_Raw = Match_Response.value?.copy()
                                // 데이터 클래스들의 배열 출력
                                Log.d("Match_Raw", Match_Raw.toString())
                                intent.putExtra("matchV", Match_Raw)
                                //요소별 접근
                                //response의 각 데이터 클래스 접근
                                if (Match_Raw != null) {
                                    Log.d("MatchItems", Match_Raw.data[0].toString())
                                    for (element in Match_Raw.data) {
                                        cardItems.add(element)
                                    }
                                }
                            }
                        })

                    supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.starFragment -> {
                    var GET_bookmark_Response = MutableLiveData<Bookmark_List>()
                    RetrofitClient.Bookmark_instance.GET_Bookmark(emailInfo)
                        .enqueue(object: Callback <Bookmark_List> {
                            override fun onFailure(call: Call<Bookmark_List>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                                Log.d(TAG, "GET B failed")
                                Log.d(TAG, t.message.toString())
                            }
                            override fun onResponse(call: Call<Bookmark_List>, response: Response<Bookmark_List>) {
//                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                                Log.d(TAG, "GET B succeeded")
                                Log.d(TAG, response.body().toString())

                                //지정한 데이터 클래스 객체로 저장
                                GET_bookmark_Response.value = response.body() as Bookmark_List
                                // 값을 복사
                                val GET_bookmarkRaw = GET_bookmark_Response.value?.copy()
                                // 데이터 클래스들의 배열 출력
                                Log.d("GET_bookmarkRaw", GET_bookmarkRaw.toString())
                                //요소별 접근
                                //response의 각 데이터 클래스 접근
                                if (GET_bookmarkRaw != null) {
                                    Log.d("GET_bookmark", GET_bookmarkRaw.Bookmark[0].toString())
                                    for (element in GET_bookmarkRaw.Bookmark){
                                        Log.d("GET_bookmark size", GET_bookmarkRaw.Bookmark.size.toString())
                                        animalDatas.add(element)
                                    }
                                }
                            }
                        }
                        )
                    supportFragmentManager.beginTransaction().replace(R.id.main_frm, StarFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.matchFragment -> {
                    val intent= Intent(this, MatchActivity::class.java)
                    Log.d(TAG, "Enter Match Fragment")

                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }

                R.id.shareFragment -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_frm, ShareFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.accountFragment -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_frm, AccountFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}
