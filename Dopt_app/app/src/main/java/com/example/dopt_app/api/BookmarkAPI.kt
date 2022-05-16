package com.example.dopt_app.api

import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.data.PostResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// https://stickode.tistory.com/43
// jsonparams
interface BookmarkAPI {
    @POST("Bookmark")
    fun POST_Bookmark(
        @Body jsonparams: Bookmark
    ): Call<PostResult>
}