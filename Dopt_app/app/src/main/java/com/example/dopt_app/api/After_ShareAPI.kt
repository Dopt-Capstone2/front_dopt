package com.example.dopt_app.api

import com.example.dopt_app.data.*
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

// https://stickode.tistory.com/43
// jsonparams
interface After_ShareAPI {
    @POST("After_Share/post")
    fun POST_After_Share(
        @Body jsonparams: After_Share
    ): Call<PostResult>

    @GET("After_Share/get")
    fun GET_After_Share(
        @Query("userEmail") userEmail: String
    ): Call<After_Share_List>

    @DELETE("After_Share/delete")
    fun DELETE_After_Share(
        @Body jsonparams: After_Share_Delete
    ):Call<PostResult>

    @POST("After_Share/update")
    fun UPDATE_After_Share(
        @Body jsonparams: After_Share
    ): Call<PostResult>
}