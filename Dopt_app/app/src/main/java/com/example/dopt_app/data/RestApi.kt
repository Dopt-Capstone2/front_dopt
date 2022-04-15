package com.example.dopt_app.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestApi {
    private val openAPIInterface: OpenAPIInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        openAPIInterface = retrofit.create(OpenAPIInterface::class.java)
    }

    fun getAnimalListRetrofit(param: Map<String, String>): Call<Response<Any?>> {
        return openAPIInterface.getInfo(param)
    }
}