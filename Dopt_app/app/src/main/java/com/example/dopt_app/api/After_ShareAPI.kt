package com.example.dopt_app.api

import com.example.dopt_app.data.After_Share
import com.example.dopt_app.data.After_Share_List
import com.example.dopt_app.data.Bookmark_List
import com.example.dopt_app.data.PostResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// https://stickode.tistory.com/43
// jsonparams
interface After_ShareAPI {
    @POST("After_Share/post")
    fun POST_After_Share(
        @Body jsonparams: After_Share
    ): Call<PostResult>

    @GET("After_Sahre/get")
    fun GET_After_Share(
        @Query("userEmail") userEmail: String
    ): Call<After_Share_List>
}