package com.example.dopt_app.api

import com.example.dopt_app.data.Match
import retrofit2.Call
import retrofit2.http.GET

// interface function 안에 어떻게 작성해야 될지 모르겠음..
interface MatchAPI {

    @GET("Match/get")
    fun GET_Match(

    ): Call<Match>
}