package com.example.dopt_app.api

import com.example.dopt_app.data.Checklist
import com.example.dopt_app.data.PostResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// https://stickode.tistory.com/43
// jsonparams
interface POST_ChecklistAPI {
    @POST("userJoin")
    fun userJoin(
        @Body jsonparams: Checklist
    ): Call<PostResult>
}