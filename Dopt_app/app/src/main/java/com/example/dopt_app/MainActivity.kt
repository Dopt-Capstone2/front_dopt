package com.example.dopt_app

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.account.AccountFragment
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.Match
import com.example.dopt_app.data.Preference
import com.example.dopt_app.databinding.ActivityMainBinding
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

// import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()

        getAnimalData_10Days()
//        getAnimalData_Monthly()



    }

//    val endde_Monthly = get_endde()
//    val bgnde_Monthly = get_bgnde(get_DATE_Sub())
//
//    var animalResponse_Monthly = MutableLiveData<OpenAnimal>()
//
//    val animalRaw_Monthly = animalResponse_Monthly.value?.copy()
    //val animalItems_Monthly = animalRaw_Monthly?.response?.body?.items


//    fun getAnimalData_Monthly(): MutableLiveData<OpenAnimal> {
//        val call = AnimalOpenAPI.AnimalRetrofitClient.animalOpenService
//
//        //call.getAnimal("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?ServiceKey=SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ%2FaKuVyMlE%2BqQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC%2Bvm6IggUOQ%3D%3D&_type=json").enqueue(object: Callback<OpenAnimal>{
//
//        call.getAnimal("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=$bgnde_Monthly&endde=$endde_Monthly&numOfRows=15000&ServiceKey=SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ%2FaKuVyMlE%2BqQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC%2Bvm6IggUOQ%3D%3D&_type=json").enqueue(object:
//            Callback<OpenAnimal> {
//            override fun onResponse(call: Call<OpenAnimal>, response: Response<OpenAnimal>){
//                animalResponse_Monthly.value = response.body() as OpenAnimal
//                //객체에 저장이 되지 않고 null만 출력해대서
//                //isSuccessful()을 달아주었더니 잘 나온다.
//                if (response.isSuccessful){
//                    Log.d("animalResponse_Monthly", "\n\n"+response.body().toString())
//                }
//                else{
//                    //Log.d("Monthly_error", ""+response.errorBody())
//                }
//
//            }
//            override fun onFailure(call: Call<OpenAnimal>, t : Throwable) {
//                t.printStackTrace()
//                //Log.d("Failed", "Failed")
//            }
//        })
//        return animalResponse_Monthly
//
//    }


    //제네릭
    //https://starrykss.tistory.com/1124

    //갯수 세기
    //https://kkh0977.tistory.com/650
    //https://tourspace.tistory.com/111
    //processState=종료(안락사)
//    fun <T> find_Mercy_Killed(a: Array<T>, Target: T): Int {
//        for (i in a.indices) {
//            if(a.contains(Target)) return i
//        }
//        return -1
//    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {
//                    //Log.d("date-1", bgnde_Monthly)
//                    val animalRaw_Monthly = animalResponse_10Days.value?.copy()
//                    //속도차이 때문에 바로 뜨지 않음.
//                    val animalItems_Monthly = animalRaw_Monthly?.response?.body?.items
//                    var mercysum = 0
//                    var rescuesum =0
//                    if (animalItems_Monthly != null) {
//                        println(animalItems_Monthly.item.size)
//                        //println(animalItems_Monthly.item[0])
//                        //val mercy = {p : Item -> p.processState == "종료(안락사)"}
//                        //println(animalItems_Monthly.item.count(mercy))
//                        //Log.d("item[0] processState", animalItems_Monthly.item[0].processState)
//                        for (i in animalItems_Monthly.item)
//                            if (i.processState=="종료(안락사)") {
//                                mercysum += 1
//                                continue
//                            }
//                            else if (i.processState=="종료(입양)") {
//                                rescuesum += 1
//                                continue
//                            }
//                        println(mercysum)
//                        println(rescuesum)
//
//                    } else{
//                        println("animal_Monthly is null")
//                    }
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
                /*R.id.matchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MatchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }*/
                R.id.matchFragment -> {
                    Log.d(TAG, "Enter Match Fragment")
                    //get 한 preference 혹은 글로벌 변수로 저장한 preference를 인자로 넘겨주세요
                    //더미 데이터
                    val data = Preference("default1", "123@123", "개", "1", "M","흰색","개")
                    //data부분에 인자 넘기기 여기서 data는 preferece 데이터 클래스 객체
                    var Match_Response = MutableLiveData<Match>()
                    //RetrofitClient.Match_instance.GET_Match(data)
                    //파라미터로 전하는 데이터 형식은 다음과 같습니다.
                    //preference의 모든 값,
                    //그리고 User_Signup의 userLoc
                    //여기서는 파라미터를 데이터클래스 객체로 전하지 않고,
                    //모두 분해서 하나씩 전합니다.
                    //deconstructor 등으로 파라미터를 전달해주세요.
                    RetrofitClient.Match_instance.GET_Match("default1","123@123","개", "1", "M", "흰색", "개", "서울특별시")
                        .enqueue(object: Callback<Match> {
                            override fun onFailure(call: Call<Match>, t: Throwable) {
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG)
                                    .show()
                                Log.d(TAG, "failed")
                                Log.d(TAG, t.message.toString())
                            }

                            override fun onResponse(call: Call<Match>, response: Response<Match>) {
                                Toast.makeText(
                                    applicationContext,
                                    response.body().toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                                Log.d(TAG, "succeeded")
                                Log.d(TAG, response.body().toString())
                                Match_Response.value = response.body() as Match
                                // 값을 복사
                                val Match_Raw = Match_Response.value?.copy()
                                // 데이터 클래스들의 배열 출력
                                Log.d("Match_Raw", Match_Raw.toString())
                                //요소별 접근
                                //response의 각 데이터 클래스 접근
                                if (Match_Raw != null) {
                                    Log.d("MatchItems", Match_Raw.data[0].toString())
                                }
                            }
                        }
                        )


                    val intent= Intent(this, MatchActivity::class.java)
                    startActivity(intent)
                    // return@setOnItemSelectedListener true
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