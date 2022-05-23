package com.example.dopt_app.api

import com.example.dopt_app.data.Bookmark_List
import com.example.dopt_app.data.Monthly_Statistics
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.Shelter_Signup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// https://stickode.tistory.com/43
// jsonparams
interface Monthly_StatisticsAPI {

    @GET("/statistics/30days/get")
    fun GET_Monthly_Statistics(
    ): Call<Monthly_Statistics>
}