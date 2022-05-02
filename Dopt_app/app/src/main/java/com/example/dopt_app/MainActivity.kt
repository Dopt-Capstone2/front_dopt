package com.example.dopt_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.data.AnimalOpenAPI
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

    }

    //공공API 함수 생성
    //비동기로 실행되기 떄문에
    //일반적으로 컴파일러가 순차적으로 코드를 컴파일하는 것과 다르게 동작한다.
    //다른 스레드에서 실행할 수도 있고,
    //그렇지 않을 수도 있으며,
    //해당 코드가 언제 종료될지 모른다는 특징을 가짐.
    //실시간 데이터 변화에 따라서 수정해야하는 상황이라면
    //observer를 사용해야함.



    val animalList1 = MutableLiveData<OpenAnimal>()

    private fun getAnimalData(): MutableLiveData<OpenAnimal> {
        val call = AnimalOpenAPI.AnimalRetrofitClient.animalOpenService

        call.getAnimal("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?ServiceKey=SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ%2FaKuVyMlE%2BqQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC%2Bvm6IggUOQ%3D%3D&_type=json").enqueue(object: Callback<OpenAnimal>{
            override fun onResponse(call: Call<OpenAnimal>, response: Response<OpenAnimal>){
                animalList1.value = response.body() as OpenAnimal
                //객체에 저장이 되지 않고 null만 출력해대서
                //isSuccessful()을 달아주었더니 잘 나온다.
                if (response.isSuccessful()){
                    Log.d("body", animalList1.toString())
                    Log.d("success", "success"+response.body().toString())
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
        return animalList1
    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    Log.d("animalList_val", getAnimalData().value.toString())
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