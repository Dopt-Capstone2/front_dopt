package com.example.dopt_app.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

//참고 블로그
//전체적인 구현에 대한 설명
//TODO: https://kumgo1d.tistory.com/57
//레트로핏 인자에 대한 자세한 설명
//TODO: https://jaejong.tistory.com/33
//주소에 접근하기 위한 인터페이스 생성
class AnimalOpenAPI {
    companion object {
        //xml 형식으로 보여주는 "&type=xml" 은 도메인에서 제거
        //주소가 '/'로 끝나지 않으면 터진다..
        //https://cishome.tistory.com/235?category=900738
        //그리고 주소에는 ?를 포함시키면 안된다.
        //https://stackoverflow.com/questions/38395460/java-lang-illegalargumentexception-baseurl-must-end-in-while-using-retrofit-2
        //okhttp 에러가 난다면 gradle에서 okhttp버전을 업데이트해준다.
        //https://stackoverflow.com/questions/56818660/how-to-fix-expected-android-api-level-21-but-was-19-in-android
        //도메인은 공공데이터 문서에 나와있는 rest 개발환경 서비스 url을 사용
        const val DOMAIN = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic/"
        const val API_KEY = "ServiceKey=SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ%2FaKuVyMlE%2BqQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC%2Bvm6IggUOQ%3D%3D"
    }
    interface AnimalOpenService{
        //무슨 이유에선지 잘 안된다..
        //@GET("sido?{api_key}&_type=json")
        //fun getAnimal(@Path("api_key") key: String) : Call<OpenAnimal>
        
        //dynamic url로 구현
        //참고 블로그
        //https://realapril.tistory.com/54
        @GET fun getAnimal(@Url url:String) : Call<OpenAnimal>
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
                //.addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
        }
        val animalOpenService: AnimalOpenService by lazy {
            retrofitClient.build().create(AnimalOpenService::class.java)
        }
    }

}
