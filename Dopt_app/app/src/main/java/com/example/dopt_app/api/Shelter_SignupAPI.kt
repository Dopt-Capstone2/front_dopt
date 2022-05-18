package com.example.dopt_app.api

import com.example.dopt_app.data.Bookmark_List
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Shelter_Signup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// https://stickode.tistory.com/43
// jsonparams
interface Shelter_SignupAPI {
    @POST("Shelter_Signup/post")
    fun POST_Shelter_Signup(
        @Body jsonparams: Shelter_Signup
    ): Call<PostResult>

    @GET("Shelter_Signup/get")
    fun GET_Shelter_Signup(
        @Query("userEmail") userEmail: String
    ): Call<Shelter_Signup>
}