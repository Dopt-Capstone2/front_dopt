package com.example.dopt_app.data

import io.reactivex.Maybe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

//참고 블로그
//https://blog.naver.com/zion830/221661486117

class UserJoinAPI {
    companion object {
        const val DOMAIN = "database-1.c0qu7ertkcgw.us-west-1.rds.amazonaws.com:3000"
    }

    interface UserJoinService {
        //TODO: 여기 경로가 정확하지 않음.
        @POST("/user/login")
        fun postLogin(
            @Body data: User_Signup
        ): Maybe<Response<User_Signup>>
    }

    object UserJoinRetrofitClient {
        private val retrofitClient: Retrofit.Builder by lazy {
            Retrofit.Builder()
                .baseUrl(UserJoinAPI.DOMAIN)
                //.addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
        }
        val userJoinService: UserJoinService by lazy {
            retrofitClient.build().create(UserJoinService::class.java)
        }
    }
}