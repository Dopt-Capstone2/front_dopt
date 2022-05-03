package com.example.dopt_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.data.AnimalOpenAPI

import com.example.dopt_app.data.Item
import com.example.dopt_app.data.Items

import com.example.dopt_app.data.OpenAnimal
import com.example.dopt_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()

        Log.d("animalList_val", getAnimalData().value.toString())

    }

    //공공API 함수 생성
    //비동기로 실행되기 떄문에
    //일반적으로 컴파일러가 순차적으로 코드를 컴파일하는 것과 다르게 동작한다.
    //다른 스레드에서 실행할 수도 있고,
    //그렇지 않을 수도 있으며,
    //해당 코드가 언제 종료될지 모른다는 특징을 가짐.
    //실시간 데이터 변화에 따라서 수정해야하는 상황이라면
    //observer를 사용해야함.



    val animalResponse = MutableLiveData<OpenAnimal>()

    private fun getAnimalData(): MutableLiveData<OpenAnimal> {
        val call = AnimalOpenAPI.AnimalRetrofitClient.animalOpenService

        //TODO: 동물을 현재일로부터 10일 전부터 조회할 것. ex) 오늘이 11일이라면 1일부터 조회.
        //TODO: 서비스키 뒤로 현재 일수를 받아 noticeSdt에 넣어주면 됨.
        //TODO: notiveSdt의 format: 20220503 (Int)
        //이전의 bgnde는 더이상 사용하지 않습니다.
        call.getAnimal("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?ServiceKey=SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ%2FaKuVyMlE%2BqQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC%2Bvm6IggUOQ%3D%3D&_type=json").enqueue(object: Callback<OpenAnimal>{
            override fun onResponse(call: Call<OpenAnimal>, response: Response<OpenAnimal>){
                animalResponse.value = response.body() as OpenAnimal
                //객체에 저장이 되지 않고 null만 출력해대서
                //isSuccessful()을 달아주었더니 잘 나온다.
                if (response.isSuccessful){
                    //val (animalBody, animalHeader) = animalResponse1.value
                    //Log.d("body", animalList.toString())
                    Log.d("success", "\n\n"+response.body().toString())
                }
                else{
                    Log.d("request_error", ""+response.errorBody())
                }

            }
            override fun onFailure(call: Call<OpenAnimal>, t : Throwable) {
                t.printStackTrace()
                Log.d("Failed", "Failed")
            }
        })
        return animalResponse
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
                    //Log.d("animalList_val", getAnimalData().value.toString())
                    val animalRaw = animalResponse.value!!.copy()
                    val animalItems = animalRaw.response.body.items
                    println(animalItems.item[0])

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