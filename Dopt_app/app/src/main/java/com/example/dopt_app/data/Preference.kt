package com.example.dopt_app.data

data class Preference (
    val name: String,
    val userEmail: String,
    val breed: String,
    //골든 리트리버
    val age: String,
    //N살의 N (1~6살)
    val sex: String,
    //M, F
    val color: String,
    //흰색, 회색, 검정, 갈색, 노랑
    val type: String
    //postman에서 나오는 [개] 에서 개 부분
)