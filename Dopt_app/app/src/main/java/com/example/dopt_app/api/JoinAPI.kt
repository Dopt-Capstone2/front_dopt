package com.example.dopt_app.api

import com.example.dopt_app.data.Join
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Field

interface JoinAPI {
    @FormUrlEncoded
    @POST("userJoin")
    fun userJoin(
        @Field("userEmail") userEmail: TextInputEditText,
        @Field("userPw") userPw: TextInputEditText
    ): Call<Join>
}