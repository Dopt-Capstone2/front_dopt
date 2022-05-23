package com.example.dopt_app.data

//TODO: (trivial) 현재 선호를 하나만 작성할 수 있음
data class Preference (
    val number: Int,
    val userEmail: String,
    val breed: String,
    val age: Int,
    val sex: Int,
    val color: String,
    val size: Int,
    val type: String
)