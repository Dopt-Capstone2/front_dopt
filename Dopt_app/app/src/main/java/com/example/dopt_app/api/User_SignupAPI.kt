package com.example.dopt_app.api

import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.User_Signup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// https://stickode.tistory.com/43
// jsonparams
interface User_SignupAPI {
    @POST("userJoin")
    fun POST_User_SignUp(
        @Body jsonparams: User_Signup
    ): Call<PostResult>

    //TODO: 처음 실행될 때 접속한 유저의 아이디를 저장해야함
    @GET("userJoin")
    fun GET_User_Signup(
        @Body jsonparams: User_Signup
    ): Call<PostResult>
}