package com.example.dopt_app.api

import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Shelter_Signup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// https://stickode.tistory.com/43
// jsonparams
interface Shelter_SignupAPI {
    @POST("shelterJoin")
    fun POST_Shelter_Signup(
        @Body jsonparams: Shelter_Signup
    ): Call<PostResult>
}