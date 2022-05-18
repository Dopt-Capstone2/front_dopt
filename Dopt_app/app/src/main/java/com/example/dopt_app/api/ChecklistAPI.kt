package com.example.dopt_app.api

import com.example.dopt_app.data.Bookmark_List
import com.example.dopt_app.data.Checklist
import com.example.dopt_app.data.PostResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// https://stickode.tistory.com/43
// jsonparams
interface ChecklistAPI {
    @POST("Checklist/post")
    fun POST_Checklist(
        @Body jsonparams: Checklist
    ): Call<PostResult>

    @GET("Checklist/get")
    fun GET_Checklist(
        @Query("userEmail") userEmail: String
    ): Call<Checklist>
}