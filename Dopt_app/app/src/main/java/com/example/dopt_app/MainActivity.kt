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
import com.example.dopt_app.data.DataX
import com.example.dopt_app.data.Item
import com.example.dopt_app.data.Match
import com.example.dopt_app.data.Preference
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

val data = Preference("default1", "123@123", "개", "1", "M","흰색","개")
//data부분에 인자 넘기기 여기서 data는 preferece 데이터 클래스 객체
var cardItems = mutableListOf<DataX>()

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
                    supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.starFragment -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_frm, StarFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.matchFragment -> {
                    val intent= Intent(this, MatchActivity::class.java)
                    Log.d(TAG, "Enter Match Fragment")
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
//                                cardItems[i].careAddr = Match_Raw.data[i].careAddr
//                                cardItems[i].careNm = Match_Raw.data[i].careNm
//                                cardItems[i].careTel = Match_Raw.data[i].careTel
//                                cardItems[i].chargeNm = Match_Raw.data[i].chargeNm
//                                cardItems[i].colorCd = Match_Raw.data[i].colorCd
//                                cardItems[i].desertionNo = Match_Raw.data[i].desertionNo
//                                cardItems[i].filename = Match_Raw.data[i].filename
//                                cardItems[i].happenDt = Match_Raw.data[i].happenDt
//                                cardItems[i].happenPlace = Match_Raw.data[i].happenPlace
//                                cardItems[i].kindCd = Match_Raw.data[i].kindCd
//                                cardItems[i].neuterYn = Match_Raw.data[i].neuterYn
//                                cardItems[i].noticeEdt = Match_Raw.data[i].noticeEdt
//                                cardItems[i].noticeNo = Match_Raw.data[i].noticeNo
//                                cardItems[i].noticeSdt = Match_Raw.data[i].noticeSdt
//                                cardItems[i].officetel = Match_Raw.data[i].officetel
//                                cardItems[i].orgNm = Match_Raw.data[i].orgNm
//                                cardItems[i].popfile = Match_Raw.data[i].popfile
//                                cardItems[i].processState = Match_Raw.data[i].processState
//                                cardItems[i].sexCd = Match_Raw.data[i].sexCd
//                                cardItems[i].specialMark = Match_Raw.data[i].specialMark
//                                cardItems[i].weight = Match_Raw.data[i].weight
                                        }
                                    }
                                }
                            })

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
