package com.example.dopt_app.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

//동물을 불러오는데 필요한 api 키
const val API_KEY = "SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ/aKuVyMlE+qQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC+vm6IggUOQ=="

//xml형식으로 받는다는 &type=xml은 삭제하였습니다.
const val BASE_URL = "http://openapi.animal.go.kr:80/openapi/service/rest/abandonmentPublicSrvc?_wadl"

interface OpenAPIInterface {
    @GET("http://openapi.animal.go.kr:80/openapi/service/rest/abandonmentPublicSrvc?_wadl")
            /**
             * REST 요청을 처리하기 위한 메서드
             * @param par QueryMap을 통해 질의한 쿼리문을 Map으로 부터 받는다.
             * @return Call<T> 콜백 인터페이스 반환, T는 주고 받을 데이터 구조
             * @QueryMap 어노테이션은 위치가 바뀌어도 동적으로 값을 받아올 수 있게 한다.
             */

    fun getInfo(@QueryMap par: Map<String, String>) : Call<Response<Any?>>


}