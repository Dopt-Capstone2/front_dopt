package com.example.dopt_app.api

import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit

// Retrofit Client 참고
//https://stackoverflow.com/questions/65699941/retrofit-post-on-android-kotlin-not-working-but-working-on-postman

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

    val instance: JoinAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(JoinAPI::class.java)
    }
}