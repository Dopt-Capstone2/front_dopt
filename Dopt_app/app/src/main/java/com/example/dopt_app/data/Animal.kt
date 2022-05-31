package com.example.dopt_app.data

import java.util.*
data class Animal (
    var age: String?= "",   //나이
    var careAddr: String?= "",  //보호소 주소
    var careNm: String?="", //보호소 이름
    var careTel: String?="",    // 보호소 전화번호
    var chargeNm: String?="",   //담당자 이름?
    var colorCd: String?="",    // 동물 색
    var desertionNo: String?="", //
    var filename: Int? = null,   //사진 파일 이름
    var happenDt: Int? = null,    // 발견 날짜
    var happenPlace: String?="",    //발견 장소
    var kindCd: String?="", // 종 ex) [개] 믹스견
    var neuterYn: String?="",   // 중성화 여부
    var noticeEdt: Int? = null,   // 공고 수정 날짜
    var noticeNo: String?="",   // 공고 번호?
    var noticeSdt: Int? = null,   // 공고 날짜?
    var officetel: String?="",   // 사무실 번호
    var orgNm: String?="",  // 사무실 장소?
    var popfile: String?="",
    var processState: String?="",   // 현재 상태 예) 보호중
    var sexCd: String?="",  // 성별
    var specialMark: String?="",    // 특징
    var weight: String?="", // 몸무게
    )

