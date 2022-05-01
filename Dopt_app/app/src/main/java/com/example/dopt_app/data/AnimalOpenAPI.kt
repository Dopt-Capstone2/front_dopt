package com.example.dopt_app.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//참고 블로그
//https://kumgo1d.tistory.com/57
//주소에 접근하기 위한 인터페이스 생성
class AnimalOpenAPI {
    companion object {
        //xml 형식으로 보여주는 "&type=xml" 은 도메인에서 제거
        const val DOMAIN = "http://openapi.animal.go.kr:80/openapi/service/rest/abandonmentPublicSrvc?_wadl"
        const val API_KEY = "SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ/aKuVyMlE+qQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC+vm6IggUOQ=="
    }
    interface AnimalOpenService{
        //TODO: 이 부분 수정이 필요함
        @GET("{api_key}/json")
        //TODO: call할 객체를 수정할 수도 있음.
        fun getAnimal(@Path("api_key") key: String) : Call<Items>
    }

    //오브젝트 클래스
    //이 클래스는 retrofit을 불러오는 기능을 갖고 있다.
    //오브젝트로 선언했기 때문에 singleton방식으로 retrofit객체를 다룰 수 있다.
    //lazy 지연 함수로 람다를 전달하여
    //앱이 구동되는 순간이 아닌
    //retrofit을 사용하는 순간에 retrofit 객체를 참조한다.
    //AnimalOpenService객체를 create의 인자로 전달한다.
    //이후 인터페이스의 get함수에 enqueue를 선언하여
    //콜백함수를 인자를 담게 만들 것이다.
    object AnimalRetrofitClient{
        private val retrofitClient: Retrofit.Builder by lazy {
            Retrofit.Builder()
                .baseUrl(AnimalOpenAPI.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
        }
        val animalOpenService: AnimalOpenService by lazy {
            retrofitClient.build().create(AnimalOpenService::class.java)
        }
    }

}
