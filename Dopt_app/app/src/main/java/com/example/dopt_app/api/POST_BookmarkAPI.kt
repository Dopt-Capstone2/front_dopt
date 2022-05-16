package com.example.dopt_app.api

import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.data.PostResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// https://stickode.tistory.com/43
// jsonparams
interface POST_BookmarkAPI {
    @POST("userJoin")
    fun userJoin(
        @Body jsonparams: Bookmark
    ): Call<PostResult>
}