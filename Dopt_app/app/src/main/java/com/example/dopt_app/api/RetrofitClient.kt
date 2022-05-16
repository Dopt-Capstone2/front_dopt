package com.example.dopt_app.api

import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

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
    private const val BASE_URL = "http://ec2-54-241-117-48.us-west-1.compute.amazonaws.com:3000"
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    //사용자 회원가입, 로그인
    val POST_User_Signup_instance: POST_User_SignupAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(POST_User_SignupAPI::class.java)
    }

    // 사용자 근황공유
    val POST_After_Share_instance: POST_After_ShareAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(POST_After_ShareAPI::class.java)
    }

    // 보호소 회원가입
    val POST_Shelter_Signup_instance: POST_Shelter_SignupAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(POST_Shelter_SignupAPI::class.java)
    }

    // 즐겨찾기 (북마크)
    val POST_Bookmark_instance: POST_BookmarkAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(POST_BookmarkAPI::class.java)
    }

    // 체크리스트
    val POST_Checklist_instance: POST_ChecklistAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(POST_ChecklistAPI::class.java)
    }

}