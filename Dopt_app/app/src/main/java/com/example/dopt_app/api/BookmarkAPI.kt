package com.example.dopt_app.api

import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.data.Bookmark_List
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.User_Signup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// https://stickode.tistory.com/43
// jsonparams
interface BookmarkAPI {
    @POST("Bookmark/post")
    fun POST_Bookmark(
        @Body jsonparams: Bookmark
    ): Call<PostResult>

    @GET("Bookmark/get")
    fun GET_Bookmark(
        @Query("userEmail") userEmail: String
    ): Call<Bookmark_List>
}