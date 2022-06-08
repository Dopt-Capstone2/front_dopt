package com.example.dopt_app.star

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.databinding.FragmentStarBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.auth.emailInfo
import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.data.Bookmark_List
import com.google.gson.Gson
import java.util.ArrayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var animalDatas = ArrayList<Bookmark>()

class StarFragment : Fragment() {
    private val TAG = "StarFragment"

    lateinit var binding: FragmentStarBinding
//    private var animalDatas = ArrayList<Bookmark>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStarBinding.inflate(inflater, container, false)

//        animalDatas.apply {
//            add( Bookmark(
//                emailInfo,"2015(년생)","경상북도 문경시 중앙로 170 (흥덕동) 종합가축병원","종합가축병원","054-552-2233","문경시 유통축산과",
//                "흑갈색","447512202200366","http://www.animal.go.kr/files/shelter/2022/05/202206021806956[1]_s.jpg",20220602,
//                "시외터미널인근","[개] 스탠다드 푸들","U",20220609,"경북-문경-2022-00181",20220602,"054-550-6282","경상북도 문경시",
//                "http://www.animal.go.kr/files/shelter/2022/05/202206021806956[1]_s.jpg","보호중","F","목줄과 칩이 업없고 치석이 많이 있음","0.2(Kg)",0
//            )
//            )
//            add(
//                Bookmark(emailInfo,"2015(년생)","경상북도 경주시 천북면 천북로 8-4 경주시 동물사랑보호센터","경주동물사랑보호센터","010-3841-6303","경주시","크림",
//                "447505202200524","http://www.animal.go.kr/files/shelter/2022/05/202205301605627_s.jpg",20220530,"양남면 동해안로 603-19 한수원 사택",
//                "[개] 아메리칸 코카 스파니엘","N",20220609,"경북-경주-2022-00509",20220530,"054-779-6305","경상북도 경주시",
//                "http://www.animal.go.kr/files/shelter/2022/05/202205301605627_s.jpg","보호중","F","쫄래쫄래 잘 따라오는 이쁜이", "0.2(Kg)",0
//            )
//            )
//        }
        getBookmark()
        // 더미데이터랑 Adapter 연결
        val starRVAdapter = StarRVAdapter(this, animalDatas)
        // 리사이클러뷰에 어댑터를 연결
        binding.starAnimalsRv.adapter = starRVAdapter

        starRVAdapter.setMyItemClickListener(object : StarRVAdapter.MyItemClickListener {
            override fun onItemClick(bookmark: Bookmark) {
                changeAnimalFragment(bookmark)
            }
            override fun onRemoveAlbum(position: Int) {
                starRVAdapter.removeItem(position)
            }
        })
        // 레이아웃 매니저 설정
        binding.starAnimalsRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)



        return binding.root
    }


    private fun getBookmark(){
        //북마크 정보 GET
        //북마크들의 배열을 호출한다
        //파라미터로 사용자의 이메일을 입력한다

        //데이터 클래스로 저장은 아래처럼 합니다.
        //정보 저장 객체
            var GET_bookmark_Response = MutableLiveData<Bookmark_List>()
            RetrofitClient.Bookmark_instance.GET_Bookmark(emailInfo)
                .enqueue(object: Callback <Bookmark_List> {
                    override fun onFailure(call: Call<Bookmark_List>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "GET B failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<Bookmark_List>, response: Response<Bookmark_List>) {
//                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "GET B succeeded")
                        Log.d(TAG, response.body().toString())

                        //지정한 데이터 클래스 객체로 저장
                        GET_bookmark_Response.value = response.body() as Bookmark_List
                        // 값을 복사
                        val GET_bookmarkRaw = GET_bookmark_Response.value?.copy()
                        // 데이터 클래스들의 배열 출력
                        Log.d("GET_bookmarkRaw", GET_bookmarkRaw.toString())
                        //요소별 접근
                        //response의 각 데이터 클래스 접근
                        if (GET_bookmarkRaw != null) {
                            Log.d("GET_bookmark", GET_bookmarkRaw.Bookmark[0].toString())
                            for (element in GET_bookmarkRaw.Bookmark){
                                Log.d("GET_bookmark size", GET_bookmarkRaw.Bookmark.size.toString())
                                animalDatas.add(element)
                            }
                        }
                    }
                }
                )
    }



    private fun changeAnimalFragment(bookmark: Bookmark) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, StarDetailFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val animalJson = gson.toJson(bookmark)
                    putString("bookmark", animalJson)
                }
            })
            .commitAllowingStateLoss()
    }
}