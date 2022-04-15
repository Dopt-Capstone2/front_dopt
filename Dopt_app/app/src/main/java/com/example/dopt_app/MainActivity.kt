package com.example.dopt_app

import com.example.dopt_app.data.Item


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dopt_app.data.API_KEY
import com.example.dopt_app.data.OpenAPIInterface
import com.example.dopt_app.data.Response
import com.example.dopt_app.databinding.ActivityMainBinding
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getMovieList(): Observable<List<Item>> {
    return Observable.create { subscriber ->
        val param = mapOf( // (1) GET 요청용 변수를 mapOf()를 사용해 지정
            "page" to "1",
            "api_key" to API_KEY
        )
        val call = api.getMovieListRetrofit(param) // (2) REST API의 요청
        val response = call.execute() // (3) 요청의 실행

        if (response.isSuccessful) {
            val animalList = response.body()?.results?.map { // (4) 응답받은 데이터에서 results읽기
                Item( // (5) 각각의 목록 요소를 데이터 클래스로 초기화
                    it.vote_count,
                    it.vote_average,
                    it.title,
                    it.release_date,
                    it.poster_path,
                    it.overview
                )
            }
            if (animalList != null) {
                subscriber.onNext(animalList) // (6) 구독자에게 데이터 변경 이벤트 알림
            }
            subscriber.onComplete()
        } else {
            subscriber.onError(Throwable(response.message()))
        }
    }
}


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.starFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, StarFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.matchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MatchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.shareFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ShareFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.accountFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, AccountFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}