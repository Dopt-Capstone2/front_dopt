package com.example.dopt_app.api

import com.example.dopt_app.data.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

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

    @DELETE("Bookmark/delete")
    fun DELETE_Bookmark(
        @Body jsonparams: Bookmark_Delete
    ):Call<PostResult>


}