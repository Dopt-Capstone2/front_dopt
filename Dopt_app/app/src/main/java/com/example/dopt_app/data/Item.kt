package com.example.dopt_app.data

import java.io.Serializable

data class Item(
    val age: String,
    val careAddr: String,
    val careNm: String,
    val careTel: String,
    val chargeNm: String,
    val colorCd: String,
    //desertionNo 는 String으로 받는다.
    val desertionNo: String,
    val filename: String,
    val happenDt: Int,
    val happenPlace: String,
    val kindCd: String,
    val neuterYn: String,
    val noticeEdt: Int,
    val noticeNo: String,
    val noticeSdt: Int,
    val officetel: String,
    val orgNm: String,
    val popfile: String,
    val processState: String,
    val sexCd: String,
    val specialMark: String,
    val weight: String
): Serializable

/*
    "age": "2022(년생)",
    "careAddr": "경상남도 거제시 사등면 두동로1길 109 (사등면, 한국자원재생공사폐비닐적재장) 거제시유기동물보호소",
    "careNm": "거제시유기동물보호소",
    "careTel": "055-639-6368",
    "chargeNm": "김부근",
    "colorCd": "연갈색 줄무늬",
    "desertionNo": 448537202200425,
    "filename": "http://www.animal.go.kr/files/shelter/2022/04/202205231405234_s.jpg",
    "happenDt": 20220523,
    "happenPlace": "일운면 스타힐스",
    "kindCd": "[고양이] 한국 고양이",
    "neuterYn": "N",
    "noticeEdt": 20220602,
    "noticeNo": "경남-거제-2022-00348",
    "noticeSdt": 20220523,
    "officetel": "055-639-6366",
    "orgNm": "경상남도 거제시",
    "popfile": "http://www.animal.go.kr/files/shelter/2022/04/202205231405234.jpg",
    "processState": "보호중",
    "sexCd": "Q",
    "specialMark": "허피스증세",
    "weight": "0.2(Kg)"*/
