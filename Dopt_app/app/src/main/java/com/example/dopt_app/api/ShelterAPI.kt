package com.example.dopt_app.api

import com.example.dopt_app.data.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

interface ShelterAPI {

    //Bookmark에 등록된 careNm과
    //Shelter_Signup에 있는 shelterNm이 일치하고,
    //선택한 desertionNo의 동물에 대하여
    //isConsidered 값을 UPDATE
    @POST("Shelter/updateBookmark")
    fun POST_Bookmark(
        @Body jsonparams: Bookmark_Update
    ): Call<PostResult>

    //Bookmark에 등록된 careNm과
    //Shelter_Signup에 있는 shelterNm이 일치하는
    //Bookmark를 GET
    @GET("Shelter/getBookmark")
    fun GET_Bookmark(
        @Query("shelterNm") shelterNm: String
    ): Call<Bookmark_List>

    //After_Share에 등록된 careNm과
    //Shelter_Signup에 있는 shelterNm이 일치하는
    //After_Share를 GET
    @GET("Shelter/getAfter_Share")
    fun GET_After_Share(
        @Query("shelterNm") shelterNm: String
    ): Call<After_Share_List>


}