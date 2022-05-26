package com.example.dopt_app.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User_Signup (
    @SerializedName("userEmail") val userEmail: String,
    @SerializedName("userPw") val userPw: String,
    @SerializedName("userNm") val userNm: String,
    @SerializedName("userLoc") val userLoc: String,
    @SerializedName("nicknm") val nicknm: String
): Serializable