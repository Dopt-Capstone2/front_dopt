package com.example.dopt_app.match

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.api.AnimalOpenAPI
import com.example.dopt_app.data.OpenAnimal
import com.example.dopt_app.data.Items
import com.example.dopt_app.data.Item
import com.example.dopt_app.databinding.FragmentMatchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//현재 날짜를 갖고오기 위한 변수
@RequiresApi(Build.VERSION_CODES.O)
val current = LocalDate.now()
@RequiresApi(Build.VERSION_CODES.O)
val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
@RequiresApi(Build.VERSION_CODES.O)
val endde = current.format(formatter)
//이전의 bgnde, endde로 조회.
val bgnde = (endde.toInt() - 10).toString()

//공공API 함수 생성
//비동기로 실행되기 떄문에
//일반적으로 컴파일러가 순차적으로 코드를 컴파일하는 것과 다르게 동작한다.
//다른 스레드에서 실행할 수도 있고,
//그렇지 않을 수도 있으며,
//해당 코드가 언제 종료될지 모른다는 특징을 가짐.
//실시간 데이터 변화에 따라서 수정해야하는 상황이라면
//observer를 사용해야함.

var animalResponse = MutableLiveData<OpenAnimal>()

fun getAnimalData(): MutableLiveData<OpenAnimal> {
    val call = AnimalOpenAPI.AnimalRetrofitClient.animalOpenService

    //call.getAnimal("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?ServiceKey=SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ%2FaKuVyMlE%2BqQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC%2Bvm6IggUOQ%3D%3D&_type=json").enqueue(object: Callback<OpenAnimal>{

    call.getAnimal("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=$bgnde&endde=$endde&numOfRows=5000&ServiceKey=SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ%2FaKuVyMlE%2BqQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC%2Bvm6IggUOQ%3D%3D&_type=json").enqueue(object:
        Callback<OpenAnimal> {
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

class MatchFragment : Fragment() {
    lateinit var binding: FragmentMatchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //호출하면서 파싱을 진행하는지
        //완료되기 전에 저장하면 객체에 null이 들어감
        //main activity에 함수를 호출...
        val animalRaw = animalResponse.value?.copy()
        val animalItems = animalRaw?.response?.body?.items
        if (animalItems != null) {
            println(animalItems.item.size)
            println(animalItems.item[0])
        }
        else{
            println("animalRaw is null")
        }
        //Log.d("animalList_val", getAnimalData().value.toString())
        binding = FragmentMatchBinding.inflate(inflater, container, false)

        return binding.root
    }
}