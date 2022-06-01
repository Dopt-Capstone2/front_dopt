package com.example.dopt_app.data

//TODO: (trivial) 현재 북마크 순서를 알 수 없음 (북마크한 순서대로 저장되긴 함.)
//TODO: 북마크 동물이 공고 기간이 지났을 시 삭제 로직 필요
//TODO: 입양 대기중인 동물은 다른 사람이 선택하지 못하도록 해야함

data class Bookmark (
    val userEmail: String,
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
    val weight: String,

    //0: 북마크에 추가
    //1: 입양 신청 상태
    //2: 입양 허가
    //3: 반려
    val isConsidered: Int
)