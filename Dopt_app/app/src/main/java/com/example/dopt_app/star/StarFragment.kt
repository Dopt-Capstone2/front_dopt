package com.example.dopt_app.star

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.databinding.FragmentStarBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.auth.emailInfo
import com.example.dopt_app.data.Animal
import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.data.Item
import com.google.gson.Gson
import java.util.ArrayList

class StarFragment : Fragment() {

    lateinit var binding: FragmentStarBinding
    private var animalDatas = ArrayList<Bookmark>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStarBinding.inflate(inflater, container, false)


        animalDatas.apply {
            add( Bookmark(
                emailInfo,"2015(년생)","경상북도 문경시 중앙로 170 (흥덕동) 종합가축병원","종합가축병원","054-552-2233","문경시 유통축산과",
                "흑갈색","447512202200366","http://www.animal.go.kr/files/shelter/2022/05/202206021806956[1]_s.jpg",20220602,
                "시외터미널인근","[개] 스탠다드 푸들","U",20220609,"경북-문경-2022-00181",20220602,"054-550-6282","경상북도 문경시",
                "http://www.animal.go.kr/files/shelter/2022/05/202206021806956[1]_s.jpg","보호중","F","목줄과 칩이 업없고 치석이 많이 있음","0.2(Kg)",0
            )
            )
            add(
                Bookmark(emailInfo,"2015(년생)","경상북도 경주시 천북면 천북로 8-4 경주시 동물사랑보호센터","경주동물사랑보호센터","010-3841-6303","경주시","크림",
                "447505202200524","http://www.animal.go.kr/files/shelter/2022/05/202205301605627_s.jpg",20220530,"양남면 동해안로 603-19 한수원 사택",
                "[개] 아메리칸 코카 스파니엘","N",20220609,"경북-경주-2022-00509",20220530,"054-779-6305","경상북도 경주시",
                "http://www.animal.go.kr/files/shelter/2022/05/202205301605627_s.jpg","보호중","F","쫄래쫄래 잘 따라오는 이쁜이", "0.2(Kg)",0
            )
            )
        }

        // 더미데이터랑 Adapter 연결
        val starRVAdapter = StarRVAdapter(animalDatas)
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