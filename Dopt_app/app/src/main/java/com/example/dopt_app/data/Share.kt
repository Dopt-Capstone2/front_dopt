package com.example.dopt_app.data

data class Share(
    var title: String?="",
    var text: String?="",
    var aniImg: Int? = null,
    var upDate: String = "",
    var userId: String?="",
    var userNm: String?="",
    var shareWk: String?="",
    // 공개 비공개
)
