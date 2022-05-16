package com.example.dopt_app.api

import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Shelter_Signup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// https://stickode.tistory.com/43
// jsonparams
interface POST_Shelter_SignupAPI {
    @POST("userJoin")
    fun userJoin(
        @Body jsonparams: Shelter_Signup
    ): Call<PostResult>
}