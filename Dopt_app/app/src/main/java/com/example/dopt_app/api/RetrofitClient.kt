package com.example.dopt_app.api

import android.util.Base64.*
import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object RetrofitClient {

    private val AUTH = "Basic "+Base64.encodeToString("admin:123456".toByteArray(), Base64.DEFAULT)

    private const val BASE_URL="ec2-54-241-117-48.us-west-1.compute.amazonaws.com"

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor{ chain ->
                val original=chain.request()

                val requestBuilder=original.newBuilder()
                    .addHeader("Authorization", "")
                    .method(original.method(), original.body())

                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()

    val instance: JoinAPI by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(JoinAPI::class.java)

    }
}