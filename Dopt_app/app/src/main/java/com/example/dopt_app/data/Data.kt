package com.example.dopt_app.data

data class Data(
    val index: Int,
    //level_0 는 pandas에서 자동으로 만들어주는 것으로 index와 같은 의미
    val level_0: Int,
    val processState: String,
    val vaule: Int
)