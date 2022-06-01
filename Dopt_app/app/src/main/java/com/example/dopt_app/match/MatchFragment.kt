package com.example.dopt_app.match

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.AnimalOpenAPI
import com.example.dopt_app.data.*
import com.example.dopt_app.databinding.FragmentMatchBinding
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_match.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

//현재 날짜를 갖고오기 위한 변수
fun get_DATE_Sub(): Int {
    val cal = Calendar.getInstance()
    cal.time = Date()
    val df: DateFormat = SimpleDateFormat("dd")
    cal.add(Calendar.DATE, -1)
    val today_DATE = df.format((cal.time))

    return today_DATE.toInt()
}
fun get_endde(): String {
    val cal = Calendar.getInstance()
    cal.time = Date()
    val df: DateFormat = SimpleDateFormat("yyyyMMdd")
    val endde = df.format((cal.time))

    return endde
}

fun get_bgnde(a: Int): String {
    val cal = Calendar.getInstance()
    cal.time = Date()
    val df: DateFormat = SimpleDateFormat("yyyyMMdd")

    cal.add(Calendar.DATE, -a)
    val bgnde = df.format((cal.time))

    return bgnde

}

//@RequiresApi(Build.VERSION_CODES.O)
//val current = LocalDate.now()
//@RequiresApi(Build.VERSION_CODES.O)
//val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
//@RequiresApi(Build.VERSION_CODES.O)
//val endde_10Days = current.format(formatter)
////이전의 bgnde, endde로 조회.
//val bgnde_10Days = (endde_10Days.toInt() - 10).toString()

//공공API 함수 생성
//비동기로 실행되기 떄문에
//일반적으로 컴파일러가 순차적으로 코드를 컴파일하는 것과 다르게 동작한다.
//다른 스레드에서 실행할 수도 있고,
//그렇지 않을 수도 있으며,
//해당 코드가 언제 종료될지 모른다는 특징을 가짐.
//실시간 데이터 변화에 따라서 수정해야하는 상황이라면
//observer를 사용해야함.

var animalResponse_10Days = MutableLiveData<OpenAnimal>()
var endde_10Days = get_endde()
var bgnde_10Days = get_bgnde(10)

fun getAnimalData_10Days(): MutableLiveData<OpenAnimal> {
    val call = AnimalOpenAPI.AnimalRetrofitClient.animalOpenService

    //call.getAnimal("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?ServiceKey=SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ%2FaKuVyMlE%2BqQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC%2Bvm6IggUOQ%3D%3D&_type=json").enqueue(object: Callback<OpenAnimal>{

    call.getAnimal("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=$bgnde_10Days&endde=$endde_10Days&numOfRows=5000&ServiceKey=SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ%2FaKuVyMlE%2BqQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC%2Bvm6IggUOQ%3D%3D&_type=json").enqueue(object:
        Callback<OpenAnimal> {
        override fun onResponse(call: Call<OpenAnimal>, response: Response<OpenAnimal>){
            animalResponse_10Days.value = response.body() as OpenAnimal
            //객체에 저장이 되지 않고 null만 출력해대서
            //isSuccessful()을 달아주었더니 잘 나온다.
            if (response.isSuccessful){
                //Log.d("animalResponse_10Days", "\n\n"+response.body().toString())
            }
            else{
                //Log.d("animalResponse_10Days_r", ""+response.errorBody())
            }

        }
        override fun onFailure(call: Call<OpenAnimal>, t : Throwable) {
            t.printStackTrace()
            //Log.d("Failed", "Failed")
        }
    })
    return animalResponse_10Days

}

class MatchFragment : Fragment()/*, CardStackListener*/ {
    lateinit var binding: FragmentMatchBinding

/*
    lateinit var cardStackAdapter: CardStackAdapter

    // 카드스택뷰의 레이아웃 매니져
    lateinit var manager : CardStackLayoutManager
*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //호출하면서 파싱을 진행하는지
        //완료되기 전에 저장하면 객체에 null이 들어감
        //main activity에 함수를 호출...
        val animalRaw_10Days = animalResponse_10Days.value?.copy()
        val animalItems_10Days = animalRaw_10Days?.response?.body?.items
        if (animalItems_10Days != null) {
            //println(animalItems_10Days.item.size)
            //println(animalItems_10Days.item[0])
        }
        else{
            //println("animalRaw is null")
        }
        //Log.d("animalList_val", getAnimalData().value.toString())
        binding = FragmentMatchBinding.inflate(inflater, container, false)

        return binding.root
    }


}