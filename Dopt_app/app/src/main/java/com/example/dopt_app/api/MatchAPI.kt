package com.example.dopt_app.api

import com.example.dopt_app.data.Match
import com.example.dopt_app.data.Preference
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

// interface function 안에 어떻게 작성해야 될지 모르겠음..
interface MatchAPI {

    @GET("Match/get")
    fun GET_Match(
        //@Query ("data") data: Preference
        @Query ("name") name: String,
        @Query ("userEmail") userEmail: String,
        @Query ("breed") breed: String,
        @Query ("age") age: String,
        @Query ("sex") sex: String,
        @Query ("color") color: String,
        @Query ("type") type: String,
        @Query ("userLoc") userLoc:String

    ): Call<Match>
}