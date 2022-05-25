package com.example.dopt_app.api

import com.example.dopt_app.data.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

interface PreferenceAPI {
    @POST("Preference/post")
    fun POST_Preference(
        @Body jsonparams: Preference
    ): Call<PostResult>

    @GET("Preference/get")
    fun GET_Preference(
        @Query("userEmail") userEmail: String
    ): Call<Preference_List>

    @DELETE("Preference/delete")
    fun DELETE_Preference(
        @Body jsonparams: Preference_Delete
    ):Call<PostResult>

    @POST("Preference/update")
    fun UPDATE_Preference(
        @Body jsonparams: Preference
    ): Call<PostResult>


}