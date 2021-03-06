package com.example.dopt_app.api

import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

// Retrofit Client 참고
//https://stackoverflow.com/questions/65699941/retrofit-post-on-android-kotlin-not-working-but-working-on-postman
//오브젝트 이름에는 언더스코어 _ 가 들어가면 안됩니다.


//api 사용 예시 : JoinActivity
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_join)
//
//        val joinBtn = findViewById<Button>(R.id.joinBtn)
//        joinBtn.setOnClickListener{
//            val userEmail= findViewById<TextInputEditText>(R.id.join_email_jo)
//            val userPw = findViewById<TextInputEditText>(R.id.join_pw_jo)
//            val data = User_Signup(userEmail.text.toString(), userPw.text.toString(), "hmin","Ilsan","nick")
//            startActivity(intent)
//
//            RetrofitClient.POST_User_Signup_instance.userJoin(data)
//                .enqueue(object: Callback<PostResult> {
//                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                        // Log.d(TAG, "Request Failed start")
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
//                        // Log.d(TAG, "Request Failed end")
//                    }
//                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
//                        // Log.d(TAG,"Request successful start")
//                        // Log.d(TAG, response.toString())
//                        // Log.d(TAG, response.body().toString())
//                        // Log.d(TAG, "Request successful end")
//                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
//                    }
//                }
//            )
//        }
//    }


object RetrofitClient {

    //https://hntown43.tistory.com/10
    //여러개의 기능을하는 api만들기

    // okHttpClient에서 connectTimeout, readTimeout, writeTimeout 설정
    // Match 정보를 읽어오는데 시간이 너무 오래걸려서 설정이 필요
    // 참고 : https://jongmin92.github.io/2018/01/31/Programming/android-customize-network-timeouts/
    private const val BASE_URL = "http://54.241.33.61:3000"
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30,TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addInterceptor {chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    // 사용자 회원가입, 로그인
    val User_Signup_instance: User_SignupAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(User_SignupAPI::class.java)
    }

    // 사용자 근황공유
    val After_Share_instance: After_ShareAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(After_ShareAPI::class.java)
    }

    // 보호소 회원가입
    val Shelter_Signup_instance: Shelter_SignupAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(Shelter_SignupAPI::class.java)
    }

    // 즐겨찾기 (북마크)
    val Bookmark_instance: BookmarkAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(BookmarkAPI::class.java)
    }

    // 체크리스트
    val Checklist_instance: ChecklistAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(ChecklistAPI::class.java)
    }

    // Preference
    val Preference_instance: PreferenceAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(PreferenceAPI::class.java)
    }

    //Shelter API
    val Shelter_instance: ShelterAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(ShelterAPI::class.java)
    }

    // 한달 통계
    val Monthly_Statistics__instance: Monthly_StatisticsAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(Monthly_StatisticsAPI::class.java)
    }

    // 추천 알고리즘(Match) 결과
    val Match_instance: MatchAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(MatchAPI::class.java)
    }
}