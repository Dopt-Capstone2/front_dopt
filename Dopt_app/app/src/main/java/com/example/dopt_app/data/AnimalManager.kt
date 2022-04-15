package com.example.dopt_app.data

import io.reactivex.Observable

class AnimalManager(private val api: RestApi = RestApi()) {
    fun getAnimalList(): Observable<List<Item>> { // Observable은 주로 생산자를 의미한다.
        return Observable.create { subscriber ->
            val param = mapOf( // (1) GET 요청용 변수를 mapOf()를 사용해 지정
                "page" to "1",
                "api_key" to API_KEY
            )
            val call = api.getAnimalListRetrofit(param) // (2) REST API의 요청
            val response = call.execute() // (3) 요청의 실행

            if (response.isSuccessful) {
                //TODO: map 부분을 위해서 https://acaroom.net/ko/blog/youngdeok/%EC%97%B0%EC%9E%AC-%EC%BD%94%ED%8B%80%EB%A6%B0-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-02-3%EB%8B%A8%EA%B3%84-%EB%8D%B0%EC%9D%B4%ED%84%B0%EC%9D%98-%EA%B5%AC%EC%84%B1%EA%B3%BC-recyclerview%EC%9D%98-%EC%96%B4%EB%8C%91%ED%84%B0
                    //이거 다시 읽어야 할 듯, 3번째 연재 순서대로 따라해보기
                val animalList = response.body()?.map { // (4) 응답받은 데이터에서 results읽기
                    Item( // (5) 각각의 목록 요소를 데이터 클래스로 초기화

                    )
                }
                if (animalList != null) {
                    subscriber.onNext(animalList) // (6) 구독자에게 데이터 변경 이벤트 알림
                }
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}