package com.example.dopt_app.data

//data class Join(val error: Boolean, val message:String)
// https://stickode.tistory.com/43
// DataModels.kt 참고

data class JoinModel (
    val userEmail: String,
    val userPw: String,
    val userNm: String,
    val userLoc: String,
    val nicknm: String
)

data class JoinResult(
    val result:String?=null
)