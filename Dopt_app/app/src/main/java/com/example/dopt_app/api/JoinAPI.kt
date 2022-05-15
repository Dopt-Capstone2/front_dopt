package com.example.dopt_app.api

import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.User_Signup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// https://stickode.tistory.com/43
// jsonparams
interface JoinAPI {
    @POST("userJoin")
    fun userJoin(
        @Body jsonparams: User_Signup
    ): Call<PostResult>
}