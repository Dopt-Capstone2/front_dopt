package com.example.dopt_app.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Shelter_Signup (
    @SerializedName("shelterEmail") val shelterEmail: String,
    @SerializedName("shelterPw") val shelterPw: String,
    @SerializedName("shelterNm") val shelterNm: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("shelterLoc") val shelterLoc: String,
    @SerializedName("busRegImg") val busRegImg: String,
    @SerializedName("shelterImg") val shelterImg: String
): Serializable