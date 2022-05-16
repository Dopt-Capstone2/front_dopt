package com.example.dopt_app.api

import com.example.dopt_app.data.After_Share
import com.example.dopt_app.data.PostResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// https://stickode.tistory.com/43
// jsonparams
interface POST_After_ShareAPI {
    @POST("userJoin")
    fun userJoin(
        @Body jsonparams: After_Share
    ): Call<PostResult>
}