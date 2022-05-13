package com.example.dopt_app.api

import com.example.dopt_app.data.JoinModel
import com.example.dopt_app.data.JoinResult
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Field

// https://stickode.tistory.com/43
// jsonparams
interface JoinAPI {
    @POST("userJoin")
    fun userJoin(
        @Body jsonparams: JoinModel
    ): Call<JoinResult>
}