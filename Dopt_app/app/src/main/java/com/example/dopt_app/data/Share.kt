package com.example.dopt_app.data

data class Share(
    var title: String?="",
    var text: String?="",
    var aniImg: Int? = null,
    var upDate: String = "",
    var userId: String?="",
    var userNm: String?="",
    var shareWk: String?="",
    var shelNm: String="",
    var lock: Boolean = true
    // 공개: false 비공개: true
)
